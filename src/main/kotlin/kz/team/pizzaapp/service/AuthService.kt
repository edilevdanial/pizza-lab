package kz.team.pizzaapp.service

import kz.team.pizzaapp.data.UserCreateDTO
import kz.team.pizzaapp.data.UserDTO
import kz.team.pizzaapp.utils.JwtUtil
import kz.team.pizzaapp.utils.PasswordUtil
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthService(
    val userService: UserService,
    val jwtUtil: JwtUtil
) {
    fun login(phone: String, password: String): ResponseEntity<AuthResponse> {
        val user = userService.getEntityByPhone(phone)

        if (user != null && PasswordUtil.matches(password, user.password)) {
            return ResponseEntity.ok(jwtUtil.generateToken(user.getDTO()))
        }

        return ResponseEntity.internalServerError().build()
    }

    fun refresh(request: RefreshRequest): ResponseEntity<AuthResponse> {
        val userDetail = jwtUtil.getUserDetailsFromToken(request.refreshToken)
        val user = userService.getByPhone(userDetail.phone)
        if (user != null && jwtUtil.validateToken(request.refreshToken)) {
            val newAccessToken = jwtUtil.generateAccessToken(user)
            return ResponseEntity.ok(AuthResponse(newAccessToken, request.refreshToken, user))
        }
        return ResponseEntity.badRequest().build()
    }

    fun register(userCreateDTO: UserCreateDTO): ResponseEntity<AuthResponse> {
        val user = userService.getByPhone(userCreateDTO.phone)
        if (user != null) {
            throw Exception("User with phone ${userCreateDTO.phone} already exists")
        }

        val userDTO = userService.save(userCreateDTO)
        return ResponseEntity.ok(jwtUtil.generateToken(userDTO))
    }
}

data class AuthRequest(val phone: String, val password: String)
data class AuthResponse(val accessToken: String, val refreshToken: String, val user: UserDTO)
data class RefreshRequest(val refreshToken: String)