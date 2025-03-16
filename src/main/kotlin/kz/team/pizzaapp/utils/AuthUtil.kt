package kz.team.pizzaapp.utils

import kz.team.pizzaapp.data.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken

class JwtAuthenticationToken(private val userPrincipal: UserPrincipal) : AbstractAuthenticationToken(emptyList()) {
    init {
        isAuthenticated = true
    }
    override fun getCredentials(): Any? = null
    override fun getPrincipal(): Any = userPrincipal
}