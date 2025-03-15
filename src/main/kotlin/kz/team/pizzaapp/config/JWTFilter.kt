package kz.team.pizzaapp.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kz.team.pizzaapp.utils.JwtAuthenticationToken
import kz.team.pizzaapp.utils.JwtUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            val username = jwtUtil.extractPhone(token)

            if (!username.isNullOrBlank() && jwtUtil.validateToken(token)) {
                val userDetails = jwtUtil.getUserDetailsFromToken(token)
                val auth = JwtAuthenticationToken(userDetails)

                SecurityContextHolder.getContext().authentication = auth
                println("User: $auth")
            }
        }
        filterChain.doFilter(request, response)
    }
}
