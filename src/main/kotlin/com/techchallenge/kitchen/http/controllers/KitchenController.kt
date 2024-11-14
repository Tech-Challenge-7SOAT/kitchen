package com.techchallenge.kitchen.http.controllers

import com.techchallenge.kitchen.http.requests.PreparationRequest
import com.techchallenge.kitchen.services.KitchenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class KitchenController(private val service: KitchenService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(ex: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(ex.message, HttpStatus.NOT_FOUND)

    @GetMapping("/{orderId}")
    fun index(@PathVariable orderId: String) = service.find(orderId)

    @PostMapping("/")
    fun store(@RequestBody request: PreparationRequest) =
        service.store(request.orderId, request.dueDate, request.status)
}
