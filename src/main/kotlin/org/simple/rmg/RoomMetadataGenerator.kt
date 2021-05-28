package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.NodeList
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.body.Parameter
import com.github.javaparser.ast.expr.LambdaExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.StringLiteralExpr
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import com.github.javaparser.ast.type.Type
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {
//	RoomMetadataGenerator().run(args[0], args[1], args[2])
	RoomMetadataGenerator().readMeasureMethod(args[0], args[1])
}

private val rxJavaTypes = setOf("Single", "Completable", "Observable", "Flowable")

class RoomMetadataGenerator {

	private val logger = logger<RoomMetadataGenerator>()

	fun run(projectPath: String, sourceSet: String, outputCsvPath: String) {
		logger.info("Generate metadata for project: $projectPath/$sourceSet")

		val moduleGeneratedSourcesDirectory = Paths.get(projectPath, "build", "generated", "source", "kapt", sourceSet)
		logger.info("Build directory: $moduleGeneratedSourcesDirectory")

		val generatedRoomDaoImplementations = moduleGeneratedSourcesDirectory.toFile()
			.walkTopDown()
			.filter { it.isFile }
			.filter(File::isJavaSourceFile)
			.map { it.readText() }
			.filter(String::containsRoomImport)
			.toList()

		when(val result = generateRoomMetadataForSources(generatedRoomDaoImplementations)) {
			is OverloadedMethodsFound -> reportOverloadedMethodsFound(result.methods)
			is Succeeded -> {
				val outputCsvFile = Paths.get(outputCsvPath).toFile()
				outputCsvFile.parentFile.mkdirs()
				writeMetadataToOutputFile(result.metadata, outputCsvFile.path)
			}
		}
	}

	private fun writeMetadataToOutputFile(csv: String, outputCsvPath: String) {
		logger.info("----- BEGIN DB METADATA -----\n\n${csv}\n\n----- END DB METADATA -----")

		logger.info("Write generated metadata to $outputCsvPath")

		with(File(outputCsvPath)) {
			writeText(csv)
		}
	}

	private fun reportOverloadedMethodsFound(overloadedMethods: Map<String, Set<String>>) {
		val errorMessage = overloadedMethods
			.map { (className, methodNames) ->
				val joinedMethodNames = methodNames.joinToString()
				"$className : [$joinedMethodNames]"
			}
			.joinToString(separator = "\n", prefix = "Found overloaded methods:\n\n")

		logger.error(errorMessage)

		throw RuntimeException(errorMessage)
	}

	fun generateRoomMetadataForSources(daoImplementations: List<String>): MetadataGenerationResult {
		val generatedRoomDaoAsts = daoImplementations
			.map { StaticJavaParser.parse(it) }
			.filter(CompilationUnit::isGeneratedRoomDao)

		val methodInfos = generatedRoomDaoAsts
			.flatMap { compilationUnit ->
				compilationUnit
					.methods()
					.map { methodDeclaration -> MethodInfo(compilationUnit, methodDeclaration) }
			}

		val foundMethodOverloads = findMethodsWithOverloads(methodInfos)

		return when {
			foundMethodOverloads.isNotEmpty() -> OverloadedMethodsFound(foundMethodOverloads)
			else -> Succeeded(methodInfos.joinToString("\n", transform = ::generateMethodCsvLine))
		}
	}

	private fun findMethodsWithOverloads(infos: List<MethodInfo>): Map<String, Set<String>> {
		return infos
			.groupBy(
				keySelector = { it.className },
				valueTransform = { it.methodName }
			)
			.mapValues { (_, methods) -> methods.removeUniqueElements() }
			.filterValues(Set<String>::isNotEmpty)
	}

	private fun generateMethodCsvLine(
		methodInfo: MethodInfo
	): String {
		return "${methodInfo.className},${methodInfo.methodName},${methodInfo.methodLineNumbers.first},${methodInfo.methodLineNumbers.last}"
	}

	fun readMeasureMethod(
		projectPath: String,
		sourceSet: String
	): List<String> {
		val measureMethodCodeTemplate = javaClass
			.classLoader
			.getResourceAsStream("MeasureMethod.java")!!
			.reader()
			.readText()
		val moduleGeneratedSourcesDirectory = Paths.get(projectPath, "build", "generated", "source", "kapt", sourceSet)

		val generatedRoomDaoImplementations = moduleGeneratedSourcesDirectory.toFile()
			.walkTopDown()
			.filter { it.isFile }
			.filter(File::isJavaSourceFile)
			.map { it.readText() }
			.filter(String::containsRoomImport)
			.toList()

		val generatedRoomDaoAsts = generatedRoomDaoImplementations
			.map { StaticJavaParser.parse(it) }
			.filter(CompilationUnit::isGeneratedRoomDao)

		val transformedAsts = generatedRoomDaoAsts
			.map { compilationUnit -> transformGeneratedDao(measureMethodCodeTemplate, compilationUnit) }
			.map(CompilationUnit::toString)

		return transformedAsts
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

		classDeclaration
			.methods
			.filter { it.type.isNotRxType() && !it.type.isVoidType }
			.onEachIndexed { index, methodDeclaration -> logger.debug("Index: $index, Method: ${methodDeclaration.nameAsString}") }
			.onEach { methodDeclaration ->
				val originalMethodBody = methodDeclaration.body.get().clone()
				val executionLambda = LambdaExpr(NodeList(), originalMethodBody)

				val newMethodBody = ReturnStmt(MethodCallExpr(
					measureMethod.nameAsString,
					StringLiteralExpr(methodDeclaration.nameAsString),
					executionLambda
				))

				methodDeclaration.setBody(BlockStmt(NodeList.nodeList(newMethodBody)))
			}

		classDeclaration.addMember(measureMethod)

		return generatedDao
	}
}

private fun Type.isNotRxType(): Boolean {
	return when {
		isClassOrInterfaceType -> asClassOrInterfaceType().nameAsString !in rxJavaTypes
		else -> true
	}
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

private data class MethodInfo(
	val className: String,
	val methodName: String,
	val methodLineNumbers: IntRange
) {

	constructor(compilationUnit: CompilationUnit, methodDeclaration: MethodDeclaration) : this(
		className = compilationUnit.className(),
		methodName = methodDeclaration.nameAsString,
		methodLineNumbers = methodDeclaration.range.get().begin.line..methodDeclaration.range.get().end.line
	)
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
