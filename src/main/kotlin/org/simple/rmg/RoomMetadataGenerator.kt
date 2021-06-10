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
	RoomMetadataGenerator().run(args[0], args[1])
}

private val rxJavaTypes = setOf("Single", "Completable", "Observable", "Flowable")

class RoomMetadataGenerator {

	private val logger = logger<RoomMetadataGenerator>()

	fun run(
		projectPath: String,
		sourceSet: String
	) {
		val measureMethodCodeTemplate = javaClass
			.classLoader
			.getResourceAsStream("MeasureMethod.java")!!
			.reader()
			.readText()
		val moduleGeneratedSourcesDirectory = Paths.get(projectPath, "build", "generated", "source", "kapt", sourceSet)

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
				val transformedAst = transformGeneratedDao(measureMethodCodeTemplate, daoMetadata.ast.clone())
				daoMetadata.replaceAst(transformedAst)
			}

		transformedAsts.forEach { daoMetadata -> daoMetadata.file.writeText(daoMetadata.ast.toString()) }
	}

	fun transformGeneratedDao(
		measureMethodCodeTemplate: String,
		generatedDao: CompilationUnit
	): CompilationUnit {
		val classDeclaration = generatedDao.types.first() as ClassOrInterfaceDeclaration
		val measureMethod = StaticJavaParser
			.parse(measureMethodCodeTemplate.replace("\$CLASS_NAME\$", classDeclaration.nameAsString))
			.types
			.first()
			.methods
			.first()

		// Check is needed in the case of local development where Gradle caching might skip generating new Room code.
		// When converting this to a Gradle plugin, should add support for the caching mechanism
		if (!classDeclaration.methods.contains(measureMethod)) {
			val methodsToTransform = classDeclaration
				.methods
				.filter(MethodDeclaration::isOverriddenPublicMethod)

			// Synchronous non-void returns
			methodsToTransform
				.filter { it.type.isNotRoomDatasource() && it.type.isNotRxType() && !it.type.isVoidType }
				.onEach { methodDeclaration ->
					val originalMethodBody = methodDeclaration.body.get().clone()
					val executionLambda = LambdaExpr(NodeList(), originalMethodBody)

					val newMethodBody = ReturnStmt(
						MethodCallExpr(
							measureMethod.nameAsString,
							StringLiteralExpr(methodDeclaration.nameAsString),
							executionLambda
						)
					)

					methodDeclaration.setBody(BlockStmt(NodeList.nodeList(newMethodBody)))
				}

			// Synchronous void returns
			methodsToTransform
				.filter { it.type.isNotRoomDatasource() && it.type.isNotRxType() && it.type.isVoidType }
				.onEach { methodDeclaration ->
					val originalMethodBody = methodDeclaration.body.get().clone().apply {
						addStatement(ReturnStmt(NullLiteralExpr()))
					}
					val executionLambda = LambdaExpr(NodeList(), originalMethodBody)

					val newMethodBody = ExpressionStmt(
						MethodCallExpr(
							measureMethod.nameAsString,
							StringLiteralExpr(methodDeclaration.nameAsString),
							executionLambda
						)
					)

					methodDeclaration.setBody(BlockStmt(NodeList.nodeList(newMethodBody)))
				}

			// Rx return types
			methodsToTransform
				.filter { it.type.isNotRoomDatasource() && it.type.isRxType() }
				.map { methodDeclaration ->
					val rxCreationStatement = methodDeclaration.body.get().statements.first { it is ReturnStmt } as ReturnStmt

					val rxCreationExpression = rxCreationStatement.expression.get() as MethodCallExpr

					if (rxCreationExpression.scope.get().asNameExpr().nameAsString != "RxRoom") {
						throw IllegalStateException("Unrecognized Rx return statement: $rxCreationStatement")
					}

					val callableMethodDeclaration = rxCreationExpression
						.arguments.first { it is ObjectCreationExpr && it.typeAsString.startsWith("Callable") }
						.childNodes.first { it is MethodDeclaration && it.nameAsString == "call" } as MethodDeclaration

					methodDeclaration to callableMethodDeclaration
				}
				.onEach { (rxMethodDeclaration, callableMethodDeclaration) ->
					val originalMethodBody = callableMethodDeclaration.body.get().clone()
					val executionLambda = LambdaExpr(NodeList(), originalMethodBody)

					val newMethodBody = ReturnStmt(
						MethodCallExpr(
							measureMethod.nameAsString,
							StringLiteralExpr(rxMethodDeclaration.nameAsString),
							executionLambda
						)
					)

					callableMethodDeclaration.setBody(BlockStmt(NodeList.nodeList(newMethodBody)))
				}

			classDeclaration.addMember(measureMethod)
		}

		return generatedDao
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
