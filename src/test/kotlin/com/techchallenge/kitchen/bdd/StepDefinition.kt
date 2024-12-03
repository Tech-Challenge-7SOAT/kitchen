package com.techchallenge.kitchen.bdd

import com.techchallenge.kitchen.KitchenApplication
import com.techchallenge.kitchen.domain.Preparation
import com.techchallenge.kitchen.http.requests.PreparationRequest
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Entao
import io.cucumber.java.pt.Quando
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration
@SpringBootTest(classes = [KitchenApplication::class], webEnvironment = WebEnvironment.DEFINED_PORT)
class StepDefinition {

    private lateinit var response: Response
    private lateinit var preparationResponse: Preparation
    private val ENDPOINT_API = "http://localhost:8081/"

    @Dado("que o usuário tenha um preparo registrado")
    fun `que o usuario tenha um preparo registrado`() {
        val request = PreparationRequest(orderId = "1", dueDate = "2022-12-31T23:59:59", status = "CONFIRMED")
        preparationResponse = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post(ENDPOINT_API)
            .then()
            .extract()
            .`as`(Preparation::class.java)
    }

    @Quando("o usuário busca o preparo por ID de pedido")
    fun `o usuario busca o preparo por ID de pedido`() {
        response = given()
            .`when`()
            .get(ENDPOINT_API + "1")
    }

    @Entao("o usuário deve visualizar o preparo")
    fun `o usuario deve visualizar o preparo`() = response.then().statusCode(200)

    @Quando("o usuário busca o preparo por ID de pedido inexistente")
    fun `o usuario busca o preparo por ID de pedido inexistente`() {
        response = given()
            .`when`()
            .get(ENDPOINT_API + "2")
    }

    @Entao("o usuário deve visualizar a mensagem de erro")
    fun `o usuario deve visualizar a mensagem de erro`() = response.then().statusCode(404)

    @Quando("o usuário envia um novo pedido para preparo")
    fun `o usuario envia um novo pedido para preparo`() {
        val request = PreparationRequest(orderId = "2", dueDate = "2022-12-31T23:59:59", status = "CONFIRMED")
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .post(ENDPOINT_API)
    }

    @Entao("o usuário deve visualizar o preparo do pedido")
    fun `o usuario deve visualizar o preparo do pedido`() = response.then().statusCode(201)

    @Quando("o usuário atualiza o status do preparo")
    fun `o usuario atualiza o status do preparo`() {
        val request = PreparationRequest(orderId = "1", dueDate = "2022-12-31T23:59:59", status = "IN_PROGRESS")
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .`when`()
            .put(ENDPOINT_API + "1")
    }

    @Entao("o usuário deve visualizar o status do preparo atualizado")
    fun `o usuario deve visualizar o status do preparo atualizado`() = response.then().statusCode(200)
}
