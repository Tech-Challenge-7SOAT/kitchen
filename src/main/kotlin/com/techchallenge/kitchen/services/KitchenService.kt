package com.techchallenge.kitchen.services

import com.techchallenge.kitchen.domain.Preparation
import com.techchallenge.kitchen.repositories.PreparationRepository
import org.springframework.stereotype.Service

@Service
class KitchenService(private val repository: PreparationRepository) {

    fun find(orderId: String) = repository.findByOrderId(orderId) ?: throw NoSuchElementException("Preparation not found")

    fun store(orderId: String, dueDate: String, status: String) =
        repository.save(Preparation(orderId = orderId, dueDate = dueDate, status = status))
}
