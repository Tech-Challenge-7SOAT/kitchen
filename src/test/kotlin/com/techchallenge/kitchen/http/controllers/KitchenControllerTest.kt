package com.techchallenge.kitchen.http.controllers

import com.techchallenge.kitchen.domain.Preparation
import com.techchallenge.kitchen.services.KitchenService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class KitchenControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {

    @Nested
    @DisplayName("GET /{orderId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetOrder {

        private val service: KitchenService = mockk(relaxed = true)

        @Test
        fun `should return OK when order exists`() {

            every { service.find("1") } returns Preparation(1L, "1", "2021-08-01T12:00:00Z", "PENDING")

            mockMvc.get("/1")
                .andExpect {
                    status { isOk() }
                }
        }

        @Test
        fun `should return 404 when order does not exist`() {

            every { service.find("does-not-exist") } throws NoSuchElementException("Preparation not found")

            mockMvc.get("/does-not-exist")
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}
