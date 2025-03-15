package kz.team.pizzaapp.config

import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {

    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Value("\${spring.datasource.driver-class-name}")
    lateinit var driver: String

    @Value("\${spring.datasource.username}")
    lateinit var username: String

    @Value("\${spring.datasource.password}")
    lateinit var password: String

    @Bean
    fun connectDatabase(): Database {
        return Database.connect(
            url = this.url,
            driver = this.driver,
            user = this.username,
            password = this.password
        )
    }
}