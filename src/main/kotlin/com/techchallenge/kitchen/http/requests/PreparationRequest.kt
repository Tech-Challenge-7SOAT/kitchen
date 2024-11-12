package com.techchallenge.kitchen.http.requests

data class PreparationRequest(
    val orderId: String,
    val dueDate: String,
    val status: String
) {}
