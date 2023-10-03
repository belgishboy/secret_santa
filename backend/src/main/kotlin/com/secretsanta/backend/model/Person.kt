package com.secretsanta.backend.model

data class Tribe(val tribe: TribeName, val persons: List<Person>)

data class Person (val id: String, val name: String, val familyName: FamilyName, val email: String)

enum class FamilyName {
    Luc,
    Denis,
    Marc,
    Bernard,
    Ghislaine,
    Gregoire,
    Sonk
}

enum class TribeName {
    Heymans,
    Erpicum
}
