package kz.team.pizzaapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtFilter: JwtFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() } // Disable CSRF
            .authorizeHttpRequests {
//                it.requestMatchers(org.springframework.http.HttpMethod.GET, "/**").permitAll() // Allow all GET requests
                it
                    .requestMatchers("/auth/**").permitAll() // Allow auth routes
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // ✅ Allow Swagger
                    .requestMatchers("/actuator/**").permitAll() // ✅ Allow actuator health
                    .anyRequest().authenticated()
            } // Protect all other endpoints
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) } // No sessions
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java) // Add JWT filter

        return http.build()
    }
}
