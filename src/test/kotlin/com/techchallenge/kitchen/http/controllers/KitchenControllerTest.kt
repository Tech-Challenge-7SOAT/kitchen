package com.techchallenge.kitchen.http.controllers

import com.techchallenge.kitchen.domain.Preparation
import com.techchallenge.kitchen.repositories.PreparationRepository
import com.techchallenge.kitchen.services.KitchenService
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
internal class KitchenControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val repository: PreparationRepository
) {

    @BeforeEach
    fun setUp() = repository.deleteAll()

    @Nested
    @DisplayName("GET /{orderId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetOrder {

        @Test
        fun `should return OK when order exists`() {

            repository.save(Preparation(1L, "1", "2021-08-01T12:00:00Z", "PENDING"))

            mockMvc.get("/1")
                .andExpect {
                    status { isOk() }
                }
        }

        @Test
        fun `should return 404 when order does not exist`() {

            mockMvc.get("/does-not-exist")
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostOrder {

        @Test
        fun `should return OK when order is stored`() {

            val performPost = mockMvc.post("/") {
                contentType = MediaType.APPLICATION_JSON
                content = """{ "orderId": "1", "dueDate": "2021-08-01T12:00:00Z", "status": "PENDING" }"""
            }

            performPost.andExpect {
                status { isOk() }
            }
        }
    }

    @Nested
    @DisplayName("PUT /{orderId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PutOrder {

        private val service: KitchenService = mockk(relaxed = true)

        @Test
        fun `should return OK when order is updated`() {

            repository.save(repository.save(Preparation(1L, "1", "2021-08-01T12:00:00Z", "PENDING")))

            val performPut = mockMvc.put("/1") {
                contentType = MediaType.APPLICATION_JSON
                content = """{ "orderId": "1", "dueDate": "2021-08-01T12:00:00Z", "status": "PENDING" }"""
            }

            performPut.andExpect {
                status { isOk() }
            }
        }
    }
}
