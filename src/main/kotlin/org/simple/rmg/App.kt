package org.simple.rmg

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
			.toList()

		logger.info("All generated Java sources: ${generatedJavaSourceFiles.joinToString("\n")}")
	}
}

private fun File.isJavaSourceFile(): Boolean = name.endsWith(".java")
