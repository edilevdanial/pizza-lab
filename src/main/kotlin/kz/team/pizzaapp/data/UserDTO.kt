package kz.team.pizzaapp.data

data class UserDTO (
    val id: Long,
    val username: String,
    val email: String,
    val phone: String,
    val address: String,
)

data class UserCreateDTO (
    val username: String,
    val email: String,
    val phone: String,
    val address: String,
    var password: String
)
