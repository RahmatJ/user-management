package id.co.vantablack.usermanagement

import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import kotlin.test.Test

@WebMvcTest(Controller::class)
class ControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    inner class GetUser {
        @Test
        fun `should return user - given valid email`() {
            val email = "mock@test.com"

            mockMvc.get("/users/$email").andExpect {
                status { isOk() }
                jsonPath("$.email") {value(email    )}
            }

        }
    }

}