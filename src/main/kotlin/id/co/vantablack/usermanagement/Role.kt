package id.co.vantablack.usermanagement

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.Instant

@Document(collection = "roles")
data class Role(
    @Id
    val id: String? = null,

    val code: String,
    val name: String,
    val permissions: List<String>,

    @CreatedDate
    val createdAt: Instant? = null,
    @LastModifiedDate
    val updatedAt: Instant? = null
)

interface RoleRepository: MongoRepository<Role, String>
