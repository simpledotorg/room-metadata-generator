package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {
	App().run(args[0], args[1], args[2])
}

class App {

	private val logger = logger<App>()

	fun run(path: String, module: String, outputCsvPath: String) {
		logger.info("Generate metadata for project: $path/$module")

		val (moduleDirectoryName, sourceSet) = module.split(':')
		val moduleRootDirectory = Paths.get(path, moduleDirectoryName)

		val moduleGeneratedSourcesDirectory =
			moduleRootDirectory.resolve(Paths.get("build", "generated", "source", "kapt", sourceSet))

		logger.info("Build directory: $moduleGeneratedSourcesDirectory")

		val generatedRoomDaoImplementations = moduleGeneratedSourcesDirectory.toFile()
			.walkTopDown()
			.filter { it.isFile }
			.filter(File::isJavaSourceFile)
			.map(File::readText)
			.filter(String::containsRoomImport)
			.toList()

		val result = generateRoomMetadataForSources(generatedRoomDaoImplementations)

		logger.info("----- BEGIN DB METADATA -----\n\n${result}\n\n----- END DB METADATA -----")

		logger.info("Write generated metadata to $outputCsvPath")

		with(File(outputCsvPath)) {
			val csv = (result as Succeeded).metadata
			writeText(csv)
		}
	}

	fun generateRoomMetadataForSources(daoImplementations: List<String>): MetadataGenerationResult {
		val generatedRoomDaoAsts = daoImplementations
			.map { StaticJavaParser.parse(it) }
			.filter(CompilationUnit::isGeneratedRoomDao)

		val methodInformationCsv = generatedRoomDaoAsts
			.flatMap { compilationUnit ->
				compilationUnit
					.methods()
					.map { methodDeclaration -> compilationUnit to methodDeclaration }
			}
			.joinToString("\n") { (compilationUnit, methodDeclaration) ->
				generateMethodCsvLine(
					methodDeclaration = methodDeclaration,
					compilationUnit = compilationUnit
				)
			}

		return Succeeded(methodInformationCsv)
	}

	private fun generateMethodCsvLine(
		methodDeclaration: MethodDeclaration,
		compilationUnit: CompilationUnit
	): String {
		val methodRange = methodDeclaration.range.get()
		return "${compilationUnit.className()},${methodDeclaration.nameAsString},${methodRange.begin.line},${methodRange.end.line}"
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
