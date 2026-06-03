package com.geoclinic.security;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getProperty("DB_URL", "jdbc:postgresql://localhost:5432/postgres"));
        config.setUsername(System.getProperty("DB_USER", "postgres"));
        config.setPassword(System.getProperty("DB_PASSWORD", "Qwerty-09"));
        config.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(config);
    }
}