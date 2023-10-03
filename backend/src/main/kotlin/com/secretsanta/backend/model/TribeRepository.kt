package com.secretsanta.backend.model

interface TribeRepository {

        fun save(person: Person)
        fun findAll(): Tribe

        fun loadById(id: String): Person

        fun loadByName(name: String): Person

        fun clear()
}