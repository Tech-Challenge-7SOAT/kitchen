package com.techchallenge.kitchen

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

@ContextConfiguration(classes = [KitchenApplication::class])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class KitchenApplicationTests {

    @Autowired
    var context: WebApplicationContext? = null

    var mvc: MockMvc? = null
}
