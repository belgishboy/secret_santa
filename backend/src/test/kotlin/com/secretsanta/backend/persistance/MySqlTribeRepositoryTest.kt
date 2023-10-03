package com.secretsanta.backend.persistance

import com.secretsanta.backend.aPersonWithAName
import com.secretsanta.backend.model.TribeName
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MySqlTribeRepositoryTest {

    val tribeName = TribeName.Heymans

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
        //given
        val vincent = aPersonWithAName("Vincent")
        val saskia = aPersonWithAName("Saskia")

        //when
        mySqlTribeRepositoryTest.save(vincent)
        mySqlTribeRepositoryTest.save(saskia)
        val persons = mySqlTribeRepositoryTest.findAll()

        //then
        assertEquals(2, persons.persons.size)
    }
}