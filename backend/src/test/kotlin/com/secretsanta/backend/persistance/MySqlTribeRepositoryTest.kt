package com.secretsanta.backend.persistance

import com.secretsanta.backend.model.NoSuchPerson
import com.secretsanta.backend.model.TribeName
import com.secretsanta.backend.model.aPersonWithAName
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MySqlTribeRepositoryTest {

    val tribeName = TribeName.Heymans
    val aPerson = aPersonWithAName("aPerson")
    val anotherPerson = aPersonWithAName("anotherPerson")

    @Autowired
    private lateinit var mySqlTribeRepositoryTest: MySqlTribeRepository

    @BeforeEach
    fun setUp() {
        mySqlTribeRepositoryTest.setTribe(tribeName)
        mySqlTribeRepositoryTest.clear()
    }

    @AfterEach
    fun tearDown() {
        mySqlTribeRepositoryTest.clear()
    }

    @Test
    fun `Persists a person`() {
        // given
        val vincent = aPersonWithAName("Vincent")
        val saskia = aPersonWithAName("Saskia")

        // when
        mySqlTribeRepositoryTest.save(vincent)
        mySqlTribeRepositoryTest.save(saskia)
        val persons = mySqlTribeRepositoryTest.findAll()

        // then
        assertEquals(2, persons.persons.size)
    }

    @Test
    fun `Load a person by Id`() {
        mySqlTribeRepositoryTest.save(aPerson)
        mySqlTribeRepositoryTest.save(anotherPerson)

        val result = mySqlTribeRepositoryTest.loadById(aPerson.id)

        assertEquals(aPerson, result)
    }

    @Test
    fun `Load a person by Name`() {
        mySqlTribeRepositoryTest.save(aPerson)
        mySqlTribeRepositoryTest.save(anotherPerson)

        val result = mySqlTribeRepositoryTest.loadByName(anotherPerson.name)

        assertEquals(anotherPerson, result)
    }

    @Test
    fun `fails on loading non existing person`() {
        mySqlTribeRepositoryTest.save(aPerson)

        assertThrows<NoSuchPerson> { mySqlTribeRepositoryTest.loadByName("non-existing") }
        assertThrows<NoSuchPerson> { mySqlTribeRepositoryTest.loadById("non-existing") }
    }
}
