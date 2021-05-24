package com.mordor.code.postgressql.service;

import com.mordor.code.postgressql.model.Employee;
import com.mordor.code.postgressql.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void createEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeName("Nitin");
        employeeRepository.save(employee);
    }
}
