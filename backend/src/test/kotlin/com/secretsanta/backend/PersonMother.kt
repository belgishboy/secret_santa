package com.secretsanta.backend

import com.secretsanta.backend.model.FamilyName
import com.secretsanta.backend.model.Person
import java.util.UUID

const val anEmail = "something@something.com"
val aFamilyName = FamilyName.Luc


fun aPersonWithAName(name: String) = Person(UUID.randomUUID().toString(), name, aFamilyName, anEmail)