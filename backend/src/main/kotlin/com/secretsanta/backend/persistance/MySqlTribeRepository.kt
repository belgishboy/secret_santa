package com.secretsanta.backend.persistance

import com.secretsanta.backend.model.*
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class MySqlTribeRepository( private val jdbcTemplate: JdbcTemplate) : TribeRepository{

    lateinit var tribeName: TribeName

    init {
        initTable()
    }

    fun setTribe (tribeName: TribeName) {
        this.tribeName = tribeName
    }

    private fun initTable() {
        jdbcTemplate.update(
                """
                CREATE TABLE if not exists ${tribeName.name}(
                id VARCHAR(36) PRIMARY KEY NOT NULL,
                name VARCHAR(32) NOT NULL,
                familyName VARCHAR(32) NOT NULL,
                email VARCHAR(128) NOT NULL,
                )
                """,
        )
    }
    override fun save(person: Person) {
        val saveStatement = "INSERT INTO ${tribeName.name} (id, name, familyName, email) VALUES (?,?,?,?)"
        jdbcTemplate.update(
                saveStatement,
                person.id,
                person.name,
                person.familyName.name,
                person.email,
        )
    }

    override fun findAll(): Tribe {
        return Tribe(tribeName,
                jdbcTemplate.query(
                        """
            SELECT id, name, familyName, email
            FROM ${tribeName.name}""",
                ) { result, _ ->
                    Person(
                            result.getString("id"),
                            result.getString("name"),
                            FamilyName.valueOf(result.getString("familyName")),
                            result.getString("email")
                    )
                },
        )
    }

    override fun loadById(id: String): Person = findAll().persons.firstOrNull { person -> person.id == id } ?: throw NoSuchPerson()

    override fun loadByName(name: String): Person = findAll().persons.firstOrNull { person -> person.name == name } ?: throw NoSuchPerson()

    override fun clear() {
        dropTable()
        initTable()
    }

    private fun dropTable() {
        jdbcTemplate.update("DROP TABLE IF EXISTS ${tribeName.name}")
    }

}