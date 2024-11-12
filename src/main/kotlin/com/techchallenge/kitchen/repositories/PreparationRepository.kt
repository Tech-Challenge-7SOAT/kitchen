package com.techchallenge.kitchen.repositories

import com.techchallenge.kitchen.domain.Preparation
import org.springframework.data.repository.CrudRepository

interface PreparationRepository : CrudRepository<Preparation, Long> {
    fun findByOrderId(orderId: String): Preparation?
}
