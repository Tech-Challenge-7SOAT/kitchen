package com.techchallenge.kitchen.services

import com.techchallenge.kitchen.repositories.PreparationRepository
import org.springframework.stereotype.Service

@Service
class KitchenService(private val repository: PreparationRepository) {

    fun find(orderId: String) = repository.findByOrderId(orderId)

    fun store(orderId: String, dueDate: String, status: String) {
        println("KitchenService")
    }
}
