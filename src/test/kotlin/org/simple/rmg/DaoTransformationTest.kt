package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class DaoTransformationTest {

	private val app = RoomMetadataGenerator()

	private val measureMethodCodeTemplate: String

	init {
		measureMethodCodeTemplate = readResourceAsString("MeasureMethod.java")
	}

	@Test
	fun `generating the metadata for a single generated dao should work as expected`() {
		// given
		val daoImplementation = readResourceAsAst("test_data/transform/source/UserRoomDao_Impl.java")

		// when
		val transformedDao = app.transformGeneratedDao(measureMethodCodeTemplate, daoImplementation)

		// then
		val expectedTransformedDao = readResourceAsAst("test_data/transform/modified/UserRoomDao_Impl.java")

		assertThat(transformedDao).isEqualTo(expectedTransformedDao)
	}

	private fun readResourceAsString(resourcePath: String): String {
		return javaClass.classLoader.getResourceAsStream(resourcePath)!!.reader().readText()
	}

	private fun readResourceAsAst(resourcePath: String): CompilationUnit {
		return StaticJavaParser.parseResource(resourcePath)
	}
}
