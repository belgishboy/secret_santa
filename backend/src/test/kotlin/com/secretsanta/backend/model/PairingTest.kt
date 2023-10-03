package com.secretsanta.backend.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class PairingTest {
    val aFamilyPerson = aPersonWithAFamily(FamilyName.Luc)
    val anotherFamilyPerson = aPersonWithAFamily(FamilyName.Denis)
    val sameFamily = aPersonWithAFamily(FamilyName.Luc)

    @Test
    fun `ensures sender is not recipient`() {
        assertDoesNotThrow { Pairing(aFamilyPerson, anotherFamilyPerson) }
        assertThrows<SamePerson> { Pairing(aFamilyPerson, aFamilyPerson) }
    }

    @Test
    fun `ensures sender is not in the same family as recipient`() {
        assertDoesNotThrow { Pairing(aFamilyPerson, anotherFamilyPerson) }
        assertThrows<SameFamily> { Pairing(aFamilyPerson, sameFamily) }
    }
}
