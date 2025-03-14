package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.LoginDTO
import kz.team.pizzaapp.data.UserCreateDTO
import kz.team.pizzaapp.utils.JwtUtil
import kz.team.pizzaapp.utils.PasswordUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    val userService: UserService,
    val jwtUtil: JwtUtil
) {
    fun login(username: String, password: String): ResponseEntity<AuthResponse> {
        val user = userService.getByPhone(username)

        if (user != null && PasswordUtil.matches(password, user.password)) {
            return ResponseEntity.ok(jwtUtil.generateToken(user.username))
        }

        return ResponseEntity.internalServerError().build()
    }

    fun refresh(request: RefreshRequest): ResponseEntity<AuthResponse> {
        val username = jwtUtil.extractUsername(request.refreshToken)
        if (username.isNotEmpty() && jwtUtil.isTokenValid(request.refreshToken, username)) {
            val newAccessToken = jwtUtil.generateAccessToken(username)
            return ResponseEntity.ok(AuthResponse(newAccessToken, request.refreshToken))
        }
        return ResponseEntity.badRequest().build()
    }

    fun register(userCreateDTO: UserCreateDTO): ResponseEntity<AuthResponse> {
        val user = userService.getByPhone(userCreateDTO.phone)
        if (user != null) {
            throw Exception("User with phone ${userCreateDTO.phone} already exists")
        }

        val userDTO = userService.save(userCreateDTO)
        return ResponseEntity.ok(jwtUtil.generateToken(userDTO.username))
    }
}

data class AuthResponse(val accessToken: String, val refreshToken: String)
data class RefreshRequest(val refreshToken: String)