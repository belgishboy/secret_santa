package com.secretsanta.backend.model

data class Pairing (val sender: Person, val recipient: Person){
    init {
        assertNotTheSamePerson()
        assertNotTheSameFamily()
    }

    private fun assertNotTheSameFamily() {
        TODO("Not yet implemented")
    }

    private fun assertNotTheSamePerson() {
        TODO("Not yet implemented")
    }
}