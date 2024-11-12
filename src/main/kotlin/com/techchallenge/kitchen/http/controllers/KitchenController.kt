package com.techchallenge.kitchen.http.controllers

import com.techchallenge.kitchen.http.requests.PreparationRequest
import com.techchallenge.kitchen.services.KitchenService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KitchenController(private val service: KitchenService) {

    @GetMapping("/{orderId}")
    fun index(@RequestParam orderId: String) = service.find(orderId)

    @PostMapping("/")
    fun store(@RequestBody request: PreparationRequest) =
        service.store(request.orderId, request.dueDate, request.status)
}
