package com.secretsanta.backend.model

import java.util.UUID

const val anEmail = "something@something.com"
val aFamilyName = FamilyName.Luc

fun aPersonWithAName(name: String) = Person(UUID.randomUUID().toString(), name, aFamilyName, anEmail)

fun aPersonWithAFamily(familyName: FamilyName) = Person(UUID.randomUUID().toString(), "aName", familyName, anEmail)
