package com.mordor.code.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "postgresSqlEntityMgrFactory",
        transactionManagerRef = "postgresSqlTransactionMgr",
        basePackages = "com.mordor.code.postgressql.repositories")
@EnableTransactionManagement
public class ConfigurePostgresSqlDb {

    @Bean(name = "postgresSqlDataSource")
    @ConfigurationProperties(prefix = "spring2.datasource2")
    public DataSource createPostgresSqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresSqlEntityMgrFactory")
    public LocalContainerEntityManagerFactoryBean postgresSqlEntityMgrFactory(final EntityManagerFactoryBuilder builder, @Qualifier("postgresSqlDataSource") final DataSource dataSource) {
        final Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.hdm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder.dataSource(dataSource).properties(properties).packages("com.mordor.code.postgressql.model").persistenceUnit("employee").build();
    }

    @Bean(name = "postgresSqlTransactionMgr")
    public PlatformTransactionManager postgresSqlTransactionMgr(@Qualifier("postgresSqlEntityMgrFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager((entityManagerFactory));
    }
}
