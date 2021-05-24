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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityMgrFactory",
        transactionManagerRef = "mySqlTransactionMgr",
        basePackages = "com.mordor.code.mysql.repositories")
@EnableTransactionManagement
public class ConfigureMySqlDB {

    @Bean(name = "mySqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource createMySqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlEntityMgrFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean mySqlEntityMgrFactory(final EntityManagerFactoryBuilder builder, @Qualifier("mySqlDataSource") final DataSource dataSource) {
        final Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.hdm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return builder.dataSource(dataSource).properties(properties).packages("com.mordor.code.mysql.model").persistenceUnit("user").build();
    }

    @Bean(name = "mySqlTransactionMgr")
    @Primary
    public PlatformTransactionManager mysqlTransactionMgr(@Qualifier("mySqlEntityMgrFactory") final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager((entityManagerFactory));
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}
