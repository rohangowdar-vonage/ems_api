package com.company.ems_api.controller;

import com.company.ems_api.entities.Employee;
import com.company.ems_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/emp")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }
}
