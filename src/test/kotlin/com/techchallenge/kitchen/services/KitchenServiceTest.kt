package com.techchallenge.kitchen.services

import com.techchallenge.kitchen.domain.Preparation
import com.techchallenge.kitchen.repositories.PreparationRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class KitchenServiceTest {

    private val repository: PreparationRepository = mockk()

    private lateinit var service: KitchenService

    @BeforeEach
    fun setUp() {
        service = KitchenService(repository)
    }

    @Test
    fun find() {
        every { repository.findByOrderId(any()) } returns Preparation(
            id = 1L,
            orderId = "1",
            dueDate = "2021-08-01T12:00:00Z",
            status = "PENDING"
        )

        val preparation = service.find("1")

        assertEquals("1", preparation.orderId)
    }

    @Test
    fun findThrowsException() {
        every { repository.findByOrderId(any()) } returns null

        assertThrows(NoSuchElementException::class.java) {
            service.find("1")
        }
    }

    @Test
    fun store() {
        every { repository.save(any<Preparation>()) } returns Preparation(
            id = 1L,
            orderId = "1",
            dueDate = "2021-08-01T12:00:00Z",
            status = "PENDING"
        )

        val preparation = service.store("1", "2021-08-01T12:00:00Z", "PENDING")

        assertEquals("1", preparation.orderId)
    }

    @Test
    fun update() {
        every { repository.findByOrderId(any()) } returns Preparation(
            id = 1L,
            orderId = "1",
            dueDate = "2021-08-01T12:00:00Z",
            status = "PENDING"
        )

        every { repository.save(any<Preparation>()) } returns Preparation(
            id = 1L,
            orderId = "1",
            dueDate = "2021-08-01T12:00:00Z",
            status = "IN_PROGRESS"
        )

        org.junit.jupiter.api.assertDoesNotThrow {
            service.update("1", "2021-08-01T12:00:00Z", "IN_PROGRESS")
        }
    }

    @Test
    fun updateThrowException() {
        every { repository.findByOrderId(any()) } returns null

        org.junit.jupiter.api.assertThrows<NoSuchElementException> {
            service.update("1", "2021-08-01T12:00:00Z", "IN_PROGRESS")
        }
    }
}
