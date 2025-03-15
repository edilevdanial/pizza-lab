package kz.team.pizzaapp.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import kz.team.pizzaapp.data.UserDTO
import kz.team.pizzaapp.data.UserPrincipal
import kz.team.pizzaapp.service.AuthResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    lateinit var jwtSecret: String
    private val expirationTime = 1000 * 60 * 15 // 15 minutes for access token
    private val refreshExpirationTime = 1000 * 60 * 60 * 24 * 7 // 7 days for refresh token


    fun generateToken(userDTO: UserDTO): AuthResponse {
        val accessToken = generateAccessToken(userDTO)
        val refreshToken = generateRefreshToken(userDTO)
        return AuthResponse(accessToken, refreshToken, userDTO)
    }

    fun generateAccessToken(userDTO: UserDTO): String {
        val keyBytes = Base64.getEncoder().encode(jwtSecret.toByteArray())
        val key = SecretKeySpec(keyBytes, "HmacSHA256")

        return Jwts.builder()
            .claim("phone", userDTO.phone)
            .claim("username", userDTO.username)
            .claim("userId", userDTO.id)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationTime)) // 1 hour expiry
            .signWith(key)
            .compact()
    }

    fun generateRefreshToken(userDTO: UserDTO): String {
        val keyBytes = Base64.getEncoder().encode(jwtSecret.toByteArray())
        val key = SecretKeySpec(keyBytes, "HmacSHA256")

        return Jwts.builder()
            .claim("username", userDTO.username)
            .claim("phone", userDTO.phone)
            .claim("userId", userDTO.id)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + refreshExpirationTime)) // 1 hour expiry
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String): Boolean {
//        val claims = extractClaims(token)
        return !isTokenExpired(token)
    }

    fun getUserDetailsFromToken(token: String): UserPrincipal {
        val claims = extractClaims(token) ?: throw Exception("Invalid token")

        return UserPrincipal(claims["userId"].toString().toLong(), claims["username"] as String, claims["phone"] as String)
    }

    fun extractPhone(token: String): String? {
        val claims = extractClaims(token) ?: throw Exception("Invalid token")

        return claims["phone"] as String
    }

    private fun extractClaims(token: String): Claims? {
        val keyBytes = Base64.getEncoder().encode(jwtSecret.toByteArray())
        val key = SecretKeySpec(keyBytes, "HmacSHA256")

        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (e: SignatureException) {
            println("Invalid JWT signature: ${e.message}")
            null // ✅ Return null if the signature is invalid
        } catch (e: ExpiredJwtException) {
            println("Expired JWT token: ${e.message}")
            null // ✅ Handle expired token
        } catch (e: Exception) {
            println("Invalid token: ${e.message}")
            null
        }
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractClaims(token)?.expiration?.before(Date()) ?: false
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
