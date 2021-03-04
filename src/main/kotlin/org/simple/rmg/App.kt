package org.simple.rmg

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
	}
}
