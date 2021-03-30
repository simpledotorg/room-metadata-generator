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

		assertThat(roomMetadata).isEqualTo(expectedMetadata)
	}

	private fun readResource(resourcePath: String): String {
		return javaClass.classLoader.getResourceAsStream(resourcePath)!!.reader().readText()
	}
}
