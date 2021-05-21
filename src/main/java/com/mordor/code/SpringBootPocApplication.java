package com.mordor.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootPocApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("mysqlDataSource")
    DataSource mySqlDS;

    @Autowired
    @Qualifier("postgresSqlDataSource")
    DataSource postgresSqlDB;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPocApplication.class, args);
    }

    @Override
    public void run(String... args) throws RuntimeException {
        JdbcTemplate mySqlJdbcTemplate = new JdbcTemplate(mySqlDS);
        JdbcTemplate postgresSqlJdbcTemplate = new JdbcTemplate(postgresSqlDB);

        String query1 = "INSERT INTO Users (user_id,username) values(1,'nitin')";
        try {
            mySqlJdbcTemplate.update(query1);
            postgresSqlJdbcTemplate.update(query1);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
