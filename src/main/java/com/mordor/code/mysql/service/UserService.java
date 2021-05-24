package com.mordor.code.mysql.service;

import com.mordor.code.mysql.model.User;
import com.mordor.code.mysql.repositories.UserRepository;
import com.mordor.code.postgressql.model.Employee;
import com.mordor.code.postgressql.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser() {
        User user = new User();
        user.setUsername("Nitin");
        userRepository.save(user);
    }
}
