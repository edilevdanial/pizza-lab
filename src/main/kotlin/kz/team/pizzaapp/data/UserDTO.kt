package kz.team.pizzaapp.data

data class UserDTO (
    val id: Long,
    val username: String,
    val email: String,
    val phone: String,
    val address: String,
    val isActive: Boolean,
    val password: String
)

data class UserCreateDTO (
    val username: String,
    val email: String,
    val phone: String,
    val address: String,
    var password: String
)
