package id.co.vantablack.usermanagement

data class InviteUserRequest(
    val email: String,
    val role: List<String>
)
