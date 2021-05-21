package com.mordor.code.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class ConfigureDB {

    @Bean(name = "mysqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource createMySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresSqlDataSource")
    @ConfigurationProperties(prefix = "spring2.datasource2")
    public DataSource createPostgresSqlDataSource() {
        return DataSourceBuilder.create().build();
    }
}
