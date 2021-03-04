package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
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

		val generatedJavaSourceFiles = moduleGeneratedSourcesDirectory.toFile()
			.walkTopDown()
			.filter { it.isFile }
			.filter(File::isJavaSourceFile)
			.map(File::readText)
			.filter(String::containsRoomImport)
			.map { StaticJavaParser.parse(it) }
			.filter(CompilationUnit::isGeneratedRoomDao)
			.map { it.className() }
			.toList()

		logger.info("All generated Java sources:\n${generatedJavaSourceFiles.joinToString("\n")}")
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
