package org.simple.rmg

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class DaoTransformationTest {

	private val app = RoomMetadataGenerator()

	private val measureMethodCodeTemplate: String

	private val reporterName = "org.simple.clinic.SqlPerformanceReporter"

	init {
		measureMethodCodeTemplate = readResourceAsString("MeasureMethod.java")
	}

	@Test
	fun `generating the metadata for a single generated dao should work as expected`() {
		// given
		val daoImplementation = readResourceAsAst("test_data/transform/source/UserRoomDao_Impl.java")

		// when
		val transformedDao = app.transformGeneratedDao(measureMethodCodeTemplate, daoImplementation, reporterName)

		// then
		val expectedTransformedDao = readResourceAsAst("test_data/transform/modified/UserRoomDao_Impl.java")

		assertThat(transformedDao).isEqualTo(expectedTransformedDao)
	}

	@Test
	fun `room paging data sources should be skipped from method profiling`() {
		// given
		val daoImplementation = readResourceAsAst("test_data/transform/source/BloodPressureMeasurementRoomDao_Impl.java")

		// when
		val transformedDao = app.transformGeneratedDao(measureMethodCodeTemplate, daoImplementation, reporterName)

		// then
		val expectedTransformedDao = readResourceAsAst("test_data/transform/modified/BloodPressureMeasurementRoomDao_Impl.java")

		assertThat(transformedDao).isEqualTo(expectedTransformedDao)
	}

	@Test
	fun `running the transformation on an already processed dao should not modify it`() {
		// given
		val daoImplementation = readResourceAsAst("test_data/transform/modified/UserRoomDao_Impl.java")

		// when
		val transformedDao = app.transformGeneratedDao(measureMethodCodeTemplate, daoImplementation, reporterName)

		// then
		val expectedTransformedDao = readResourceAsAst("test_data/transform/modified/UserRoomDao_Impl.java")

		assertThat(transformedDao).isEqualTo(expectedTransformedDao)
	}

	@Test
	fun `transforming a DAO with overloads must throw an error`() {
		// given
		val daoImplementation = readResourceAsAst("test_data/transform/source/UserRoomDaoWithOverloads_Impl.java")

		// then
		assertThrows(RuntimeException::class.java) {
			app.transformGeneratedDao(measureMethodCodeTemplate, daoImplementation, reporterName)
		}
	}

	@Test
	fun `running transformation should not modify room database file`() {
		// given
		val databaseImplementation = readResourceAsAst("test_data/transform/source/AppDatabase_Impl.java")

		// when
		val transformedDatabaseImplementation = app.transformGeneratedDao(measureMethodCodeTemplate, databaseImplementation, reporterName)

		// then
		val expectedDatabaseImplementation = readResourceAsAst("test_data/transform/source/AppDatabase_Impl.java")

		assertThat(transformedDatabaseImplementation).isEqualTo(expectedDatabaseImplementation)
	}

	private fun readResourceAsString(resourcePath: String): String {
		return javaClass.classLoader.getResourceAsStream(resourcePath)!!.reader().readText()
	}

	private fun readResourceAsAst(resourcePath: String): CompilationUnit {
		return StaticJavaParser.parseResource(resourcePath)
	}
}
