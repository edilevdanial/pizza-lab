package kz.team.pizzaapp.controller

import kz.team.pizzaapp.data.UserCreateDTO
import kz.team.pizzaapp.service.AuthRequest
import kz.team.pizzaapp.service.AuthResponse
import kz.team.pizzaapp.service.AuthService
import kz.team.pizzaapp.service.RefreshRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginDTO: AuthRequest): ResponseEntity<AuthResponse> {
        return authService.login(loginDTO.username, loginDTO.password)
    }

    @PostMapping("/refresh")
    fun register(@RequestBody refreshRequest: RefreshRequest): ResponseEntity<AuthResponse> {
        return authService.refresh(refreshRequest)
    }

    @PostMapping("/register")
    fun register(@RequestBody userCreateDTO: UserCreateDTO): ResponseEntity<AuthResponse> {
        return authService.register(userCreateDTO)
    }
}