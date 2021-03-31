package org.simple.rmg

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class MetadataGenerationTest {

	private val app = App()

	@Test
	fun `generating the metadata for a single generated dao should work as expected`() {
		// given
		val daoImplementation = readResource("test_data/UserRoomDao_Impl.java")

		// when
		val roomMetadata = app.generateRoomMetadataForSources(listOf(daoImplementation))

		// then
		val expectedMetadata = """
			|UserRoomDao_Impl,createOrUpdate,184,194
			|UserRoomDao_Impl,deleteUser,196,206
			|UserRoomDao_Impl,updateLoggedInStatusForUser,208,237
			|UserRoomDao_Impl,setCurrentFacility,239,260
			|UserRoomDao_Impl,user,262,346
			|UserRoomDao_Impl,userImmediate,348,424
			|UserRoomDao_Impl,userCount,426,461
			|UserRoomDao_Impl,currentFacility,463,598
			|UserRoomDao_Impl,currentFacilityImmediate,600,727
			|UserRoomDao_Impl,currentFacilityUuid,729,749
			|UserRoomDao_Impl,userAndFacilityDetails,751,786
		""".trimMargin()

		assertThat(roomMetadata).isEqualTo(Succeeded(expectedMetadata))
	}

	@Test
	fun `generating the metadata for multiple generated dao should generate the metadata for all`() {
		// given
		val userDaoImplementation = readResource("test_data/UserRoomDao_Impl.java")
		val overdueAppointmentDaoImplementation = readResource("test_data/OverdueAppointmentRoomDao_Impl.java")

		// when
		val roomMetadata = app.generateRoomMetadataForSources(listOf(userDaoImplementation, overdueAppointmentDaoImplementation))

		// then
		val expectedMetadata = """
			|UserRoomDao_Impl,createOrUpdate,184,194
			|UserRoomDao_Impl,deleteUser,196,206
			|UserRoomDao_Impl,updateLoggedInStatusForUser,208,237
			|UserRoomDao_Impl,setCurrentFacility,239,260
			|UserRoomDao_Impl,user,262,346
			|UserRoomDao_Impl,userImmediate,348,424
			|UserRoomDao_Impl,userCount,426,461
			|UserRoomDao_Impl,currentFacility,463,598
			|UserRoomDao_Impl,currentFacilityImmediate,600,727
			|UserRoomDao_Impl,currentFacilityUuid,729,749
			|UserRoomDao_Impl,userAndFacilityDetails,751,786
			|OverdueAppointmentRoomDao_Impl,overdueAtFacility,65,319
			|OverdueAppointmentRoomDao_Impl,overdueAtFacilityDataSource,321,570
			|OverdueAppointmentRoomDao_Impl,overdueAtFacilityCount,572,645
			|OverdueAppointmentRoomDao_Impl,latestForPatient,647,884
		""".trimMargin()

		assertThat(roomMetadata).isEqualTo(Succeeded(expectedMetadata))
	}

	@Test
	fun `generating metadata for dao with overloaded methods should surface the error`() {
		// given
		val daoImplementation = readResource("test_data/TeleconsultRecordRoomDao_Impl.java")

		// when
		val result = app.generateRoomMetadataForSources(listOf(daoImplementation))

		// then
		val expectedResult = OverloadedMethodsFound(mapOf(
			"TeleconsultRecordRoomDao_Impl" to setOf("count")
		))

		assertThat(result).isEqualTo(expectedResult)
	}

	@Test
	fun `generating metadata for dao with multiple overloaded methods should surface the error`() {
		// given
		val daoImplementation = readResource("test_data/BloodPressureMeasurementRoomDao_Impl.java")

		// when
		val result = app.generateRoomMetadataForSources(listOf(daoImplementation))

		// then
		val expectedResult = OverloadedMethodsFound(mapOf(
			"BloodPressureMeasurementRoomDao_Impl" to setOf("updateSyncStatus", "count")
		))

		assertThat(result).isEqualTo(expectedResult)
	}

	@Test
	fun `generating metadata for multiple daos with overloaded methods should surface the error`() {
		// given
		val teleconsultRecordDaoImplementation = readResource("test_data/TeleconsultRecordRoomDao_Impl.java")
		val bloodPressureMeasurementDaoImplementation = readResource("test_data/BloodPressureMeasurementRoomDao_Impl.java")

		// when
		val result = app.generateRoomMetadataForSources(listOf(teleconsultRecordDaoImplementation, bloodPressureMeasurementDaoImplementation))

		// then
		val expectedResult = OverloadedMethodsFound(mapOf(
			"TeleconsultRecordRoomDao_Impl" to setOf("count"),
			"BloodPressureMeasurementRoomDao_Impl" to setOf("updateSyncStatus", "count")
		))

		assertThat(result).isEqualTo(expectedResult)
	}

	private fun readResource(resourcePath: String): String {
		return javaClass.classLoader.getResourceAsStream(resourcePath)!!.reader().readText()
	}
}
