package com.techchallenge.kitchen.http.controllers

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
class KitchenControllerTest @Autowired constructor(val mockMvc: MockMvc) {

    @Nested
    @DisplayName("GET /{orderId}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetOrder {

        @Test
        fun `should return OK when order exists`() {
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
}
