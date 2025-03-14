package kz.team.pizzaapp.config

import javax.sql.DataSource
import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {

    @Bean
    fun connectDatabase(dataSource: DataSource): Database {
        return Database.connect(dataSource)
    }
}