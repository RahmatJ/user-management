package id.co.vantablack.usermanagement

import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.client.RestTestClient
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureRestTestClient
class ControllerTest {

    @Autowired
    lateinit var client: RestTestClient

    @Nested
    inner class GetUser {
        @Test
        fun `should return user - given valid email`() {
            val email = "mock@test.com"

            client.get().uri("/users/$email")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.email").isEqualTo(email)
        }
    }

}