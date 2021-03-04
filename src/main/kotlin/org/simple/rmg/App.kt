package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.body.MethodDeclaration
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {
	App().run(args[0], args[1])
}

class App {

	private val logger = logger<App>()

	fun run(path: String, module: String) {
		logger.info("Generate metadata for project: $path/$module")

		val (moduleDirectoryName, sourceSet) = module.split(':')
		val moduleRootDirectory = Paths.get(path, moduleDirectoryName)

		val moduleGeneratedSourcesDirectory =
			moduleRootDirectory.resolve(Paths.get("build", "generated", "source", "kapt", "qaDebug"))

		logger.info("Build directory: $moduleGeneratedSourcesDirectory")

		val generatedRoomDaoAsts = moduleGeneratedSourcesDirectory.toFile()
			.walkTopDown()
			.filter { it.isFile }
			.filter(File::isJavaSourceFile)
			.map(File::readText)
			.filter(String::containsRoomImport)
			.map { StaticJavaParser.parse(it) }
			.filter(CompilationUnit::isGeneratedRoomDao)
			.toList()

		logger.info("All generated Java sources:\n${generatedRoomDaoAsts.joinToString(separator = "\n", transform = CompilationUnit::className)}")

		val methodInformations = generatedRoomDaoAsts
			.flatMap { compilationUnit ->
				compilationUnit
					.methods()
					.map { methodDeclaration -> compilationUnit to methodDeclaration }
			}
			.map { (compilationUnit, methodDeclaration) ->
				val methodRange = methodDeclaration.range
				"${compilationUnit.className()}.${methodDeclaration.nameAsString} [${methodRange.get()}]"
			}
			.toList()
			.joinToString("\n")

		logger.info("Methods:\n$methodInformations")
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
