package com.secretsanta.backend.model

import java.util.UUID

const val anEmail = "something@something.com"
val aFamilyName = FamilyName.Luc


fun aPersonWithAName(name: String) = Person(UUID.randomUUID().toString(), name, aFamilyName, anEmail)