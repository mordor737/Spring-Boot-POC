package com.mordor.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class SpringBootPocApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("mySqlDataSource")
    DataSource mySqlDS;

    @Autowired
    @Qualifier("postgresSqlDataSource")
    DataSource postgresSqlDB;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPocApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*JdbcTemplate mySqlJdbcTemplate = new JdbcTemplate(mySqlDS);
        JdbcTemplate postgresSqlJdbcTemplate = new JdbcTemplate(postgresSqlDB);

        String userQuery = "INSERT INTO User (user_id,username) values(1,'nitin')";
        String employeeQuery = "INSERT INTO Employee (user_id,employeename) values(1,'nitin')";
        try {
            mySqlJdbcTemplate.update(userQuery);
            postgresSqlJdbcTemplate.update(employeeQuery);
        } catch (Exception e) {
            throw new Exception(e);
        }*/
    }
}
