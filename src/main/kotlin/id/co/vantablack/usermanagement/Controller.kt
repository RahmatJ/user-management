package id.co.vantablack.usermanagement

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

data class UserResponse(
    val email: String,
)

@Controller
class Controller {

    @GetMapping("users/{email}")
    fun getUser(@PathVariable email: String): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(UserResponse(email))
    }

}