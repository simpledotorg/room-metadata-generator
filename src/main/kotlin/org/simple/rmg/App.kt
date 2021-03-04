package org.simple.rmg

fun main(args: Array<String>) {
	App().run(args[0])
}

class App {

	private val logger = logger<App>()

	fun run(path: String) {
		logger.info("Generate metadata for project: $path")
	}
}
