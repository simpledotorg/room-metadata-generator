package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.LambdaExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.NullLiteralExpr
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.expr.StringLiteralExpr
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import com.github.javaparser.ast.type.Type
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {
	RoomMetadataGenerator().run(args[0], args[1], args[2])
}

private val rxJavaTypes = setOf("Single", "Completable", "Observable", "Flowable")

private typealias MethodCategoryFilter = (MethodDeclaration) -> Boolean
private typealias MethodTransformer = (MethodDeclaration, MethodDeclaration) -> Unit

class RoomMetadataGenerator {

	private val logger = logger<RoomMetadataGenerator>()

	private val measureMethodCodeTemplate = javaClass
		.classLoader
		.getResourceAsStream("MeasureMethod.java")!!
		.reader()
		.readText()

	private val methodTransformerLookup: Set<Pair<MethodCategoryFilter, MethodTransformer>> = setOf(
		::isNonVoidReturn to { method, measureMethod -> transformNonVoidReturnMethod(method, measureMethod) },
		::isVoidReturn to { method, measureMethod -> transformVoidReturn(method, measureMethod) },
		::isRxReturn to { method, measureMethod -> transformRxReturn(method, measureMethod) }
	)

	fun run(
		projectPath: String,
		sourceSet: String,
		reporterName: String
	) {
		val moduleGeneratedSourcesDirectory = Paths.get(projectPath, "build", "generated", "ksp", sourceSet, "java")

		logger.info("Processing room generated sources at $moduleGeneratedSourcesDirectory")

		val generatedRoomDaoAsts = moduleGeneratedSourcesDirectory.toFile()
			.walkTopDown()
			.filter { it.isFile }
			.filter(File::isJavaSourceFile)
			.map { sourceFile -> sourceFile to sourceFile.readText() }
			.filter { (_, content) -> content.containsRoomImport() }
			.map { (sourceFile, content) -> DaoMetadata(sourceFile, StaticJavaParser.parse(content)) }
			.filter { daoMetadata -> daoMetadata.ast.isGeneratedRoomDao() }
			.toList()

		val transformedAsts = generatedRoomDaoAsts
			.map { daoMetadata ->
				val transformedAst = transformGeneratedDao(measureMethodCodeTemplate, daoMetadata.ast.clone(), reporterName)
				daoMetadata.replaceAst(transformedAst)
			}

		transformedAsts
			.onEach { daoMetadata -> logger.info("Writing transformed DAO to ${daoMetadata.file.path}") }
			.forEach { daoMetadata -> daoMetadata.file.writeText(daoMetadata.ast.toString()) }
	}

	fun transformGeneratedDao(
		measureMethodCodeTemplate: String,
		generatedDao: CompilationUnit,
		reporterName: String
	): CompilationUnit {
		val classDeclaration = generatedDao.types.first() as ClassOrInterfaceDeclaration
		val measureMethod = StaticJavaParser
			.parse(
				measureMethodCodeTemplate
					.replace("\$CLASS_NAME\$", classDeclaration.nameAsString)
					.replace("\$REPORTER_NAME\$", reporterName)
			)
			.types
			.first()
			.methods
			.first()

		val methodsToTransform = classDeclaration
			.methods
			.filter(MethodDeclaration::isOverriddenPublicMethod)

		val allMethodNames = methodsToTransform.map { it.nameAsString }
		val methodsWithOverloads = allMethodNames.removeUniqueElements()

		if (methodsWithOverloads.isNotEmpty()) {
			val message =
				"Found overloaded methods [${methodsWithOverloads.joinToString()}] in DAO: ${classDeclaration.nameAsString}"

			throw RuntimeException(message)
		}

		// Check is needed in the case of local development where Gradle caching might skip generating new Room code.
		// When converting this to a Gradle plugin, should add support for the caching mechanism
		if (!classDeclaration.methods.contains(measureMethod)) {
			methodsToTransform
				.onEach { methodDeclaration ->
					val transformer = methodTransformerLookup
						.find { (filter, _) -> filter.invoke(methodDeclaration) }
						?.second

					transformer?.invoke(methodDeclaration, measureMethod)
				}

			classDeclaration.addMember(measureMethod)
		}

		return generatedDao
	}

	private fun transformRxReturn(
		methodDeclaration: MethodDeclaration,
		measureMethod: MethodDeclaration
	) {
		val rxCreationStatement = methodDeclaration.body.get().statements.first { it is ReturnStmt } as ReturnStmt

		val rxCreationExpression = rxCreationStatement.expression.get() as MethodCallExpr

		if (rxCreationExpression.scope.get().asNameExpr().nameAsString != "RxRoom") {
			throw IllegalStateException("Unrecognized Rx return statement: $rxCreationStatement")
		}

		val callableMethodDeclaration = rxCreationExpression
			.arguments.first { it is ObjectCreationExpr && it.typeAsString.startsWith("Callable") }
			.childNodes.first { it is MethodDeclaration && it.nameAsString == "call" } as MethodDeclaration
		instrumentNonVoidReturn(callableMethodDeclaration, measureMethod, methodDeclaration.nameAsString)
	}

	private fun isRxReturn(method: MethodDeclaration) =
		method.type.isNotRoomDatasource() && method.type.isRxType()

	private fun transformVoidReturn(
		methodDeclaration: MethodDeclaration,
		measureMethod: MethodDeclaration
	) {
		val methodName = methodDeclaration.nameAsString
		instrumentVoidReturn(methodDeclaration, measureMethod, methodName)
	}

	private fun isVoidReturn(method: MethodDeclaration) =
		method.type.isNotRoomDatasource() && method.type.isNotRxType() && method.type.isVoidType

	private fun transformNonVoidReturnMethod(
		methodDeclaration: MethodDeclaration,
		measureMethod: MethodDeclaration
	) {
		val methodName = methodDeclaration.nameAsString
		instrumentNonVoidReturn(methodDeclaration, measureMethod, methodName)
	}

	private fun isNonVoidReturn(method: MethodDeclaration) =
		method.type.isNotRoomDatasource() && method.type.isNotRxType() && !method.type.isVoidType

	private fun instrumentNonVoidReturn(
		methodDeclaration: MethodDeclaration,
		measureMethod: MethodDeclaration,
		methodName: String
	) {
		val originalMethodBody = methodDeclaration.body.get().clone()
		val executionLambda = LambdaExpr(NodeList(), originalMethodBody)

		val newMethodBody = ReturnStmt(
			MethodCallExpr(
				measureMethod.nameAsString,
				StringLiteralExpr(methodName),
				executionLambda
			)
		)

		methodDeclaration.setBody(BlockStmt(NodeList.nodeList(newMethodBody)))
	}

	private fun instrumentVoidReturn(
		methodDeclaration: MethodDeclaration,
		measureMethod: MethodDeclaration,
		methodName: String?
	) {
		val originalMethodBody = methodDeclaration.body.get().clone().apply {
			addStatement(ReturnStmt(NullLiteralExpr()))
		}
		val executionLambda = LambdaExpr(NodeList(), originalMethodBody)

		val newMethodBody = ExpressionStmt(
			MethodCallExpr(
				measureMethod.nameAsString,
				StringLiteralExpr(methodName),
				executionLambda
			)
		)

		methodDeclaration.setBody(BlockStmt(NodeList.nodeList(newMethodBody)))
	}
}

private fun Type.isNotRxType(): Boolean {
	return when {
		isClassOrInterfaceType -> asClassOrInterfaceType().nameAsString !in rxJavaTypes
		else -> true
	}
}

private fun Type.isRxType(): Boolean {
	return when {
		isClassOrInterfaceType -> asClassOrInterfaceType().nameAsString in rxJavaTypes
		else -> false
	}
}

private fun Type.isNotRoomDatasource(): Boolean {
	return when {
		isClassOrInterfaceType -> asClassOrInterfaceType().nameWithScope != "DataSource.Factory"
		else -> true
	}
}

private fun MethodDeclaration.isOverriddenPublicMethod(): Boolean {
	return this.isPublic && isAnnotationPresent("Override")
}

private fun File.isJavaSourceFile(): Boolean = name.endsWith(".java")

private fun String.containsRoomImport(): Boolean {
	return contains("import androidx.room.RoomDatabase;")
}

private fun CompilationUnit.className(): String {
	return types.first().nameAsString
}

private fun CompilationUnit.isGeneratedRoomDao(): Boolean {
	return className().contains("_Impl")
}

private fun CompilationUnit.methods(): List<MethodDeclaration> {
	return types.first().methods
}

private data class DaoMetadata(
	val file: File,
	val ast: CompilationUnit
) {
	fun replaceAst(newAst: CompilationUnit) = copy(ast = newAst)
}

private fun List<String>.removeUniqueElements(): Set<String> {
	return associateBy(
		keySelector = { it },
		valueTransform = { methodName -> this.count { it == methodName } }
	)
		.filterValues { countOfMethodNames -> countOfMethodNames > 1 }
		.keys
		.toSet()
}
