package id.co.vantablack.usermanagement

import org.instancio.Instancio
import org.instancio.Select.field
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.ClassTemplate
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.web.servlet.client.RestTestClient
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mongodb.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureRestTestClient
class ControllerTest {
    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var client: RestTestClient

    @Autowired
    lateinit var permissionRepository: PermissionRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    companion object {
        @Container
        @ServiceConnection
        @JvmStatic
        val mongo = MongoDBContainer(DockerImageName.parse("mongo:8.0"))
    }

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

    @Nested
    inner class InviteUser {

        @BeforeEach
        fun beforeEach() {
            val firstPermission = Instancio.of(Permission::class.java).create()
            val secondPermission = Instancio.of(Permission::class.java).create()

            permissionRepository
                .saveAll(listOf(firstPermission, secondPermission))

            val firstRole = Instancio.of(Role::class.java)
                .set(
                    field(Role::permissions), listOf(firstPermission.code, secondPermission.code)
                ).create()

//            TODO(Rahmat): Complete this
        }

        @AfterEach
        fun afterEach() {

        }

        @Test
        fun `should invite user and create PENDING user - given valid email`() {
            val email = "mock@mail.com"
            val payload = InviteUserRequest(
                email = email,
                role = emptyList()
            )

            client.post().uri("/users/invite")
                .body(payload)
                .exchange()
                .expectStatus().isOk()
        }
    }

}