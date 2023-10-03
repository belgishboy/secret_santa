package com.secretsanta.backend.model

data class Pairing(val sender: Person, val recipient: Person) {
    init {
        assertNotTheSamePerson()
        assertNotTheSameFamily()
    }
    private fun assertNotTheSamePerson() {
        if (sender == recipient) {
            throw SamePerson()
        }
    }

    private fun assertNotTheSameFamily() {
        if (sender.familyName == recipient.familyName) {
            throw SameFamily()
        }
    }
}
