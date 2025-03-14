package kz.team.pizzaapp.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import kz.team.pizzaapp.data.UserDTO
import kz.team.pizzaapp.service.AuthResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String
    private val expirationTime = 1000 * 60 * 15 // 15 minutes for access token
    private val refreshExpirationTime = 1000 * 60 * 60 * 24 * 7 // 7 days for refresh token


    fun generateToken(username: String, userDTO: UserDTO): AuthResponse {
        val accessToken = generateAccessToken(username)
        val refreshToken = generateRefreshToken(username)
        return AuthResponse(accessToken, refreshToken, userDTO)
    }

    fun generateAccessToken(username: String): String {
        val keyBytes = Base64.getEncoder().encode(jwtSecret.toByteArray())
        val key = SecretKeySpec(keyBytes, "HmacSHA256")

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationTime)) // 1 hour expiry
            .signWith(key)
            .compact()
    }

    fun isTokenValid(token: String, username: String): Boolean {
        return extractUsername(token) == username && !isTokenExpired(token)
    }

    fun generateRefreshToken(username: String): String {
        val keyBytes = Base64.getEncoder().encode(jwtSecret.toByteArray())
        val key = SecretKeySpec(keyBytes, "HmacSHA256")

        return Jwts.builder()
            .subject(username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + refreshExpirationTime)) // 1 hour expiry
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String, username: String): Boolean {
        val claims = extractClaims(token)
        return claims.subject == username && !isTokenExpired(token)
    }

    fun extractUsername(token: String): String {
        return extractClaims(token).subject
    }

    private fun extractClaims(token: String): Claims {
        val keyBytes = Base64.getEncoder().encode(jwtSecret.toByteArray())
        val key = SecretKeySpec(keyBytes, "HmacSHA256")

        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractClaims(token).expiration.before(Date())
    }
}

object PasswordUtil {
    private val encoder = BCryptPasswordEncoder()

    fun encode(password: String): String {
        return encoder.encode(password)
    }

    fun matches(rawPassword: String, encodedPassword: String): Boolean {
        return encoder.matches(rawPassword, encodedPassword)
    }
}
