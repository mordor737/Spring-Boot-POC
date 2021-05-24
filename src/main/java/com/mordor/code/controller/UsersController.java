package com.mordor.code.controller;

import com.mordor.code.mysql.service.UserService;
import com.mordor.code.postgressql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/insert")
    public String createUser() {
        userService.createUser();
        employeeService.createEmployee();
        return "Inserting User records";
    }

}
