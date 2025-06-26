package com.company.ems_api.service;

import com.company.ems_api.entities.Employee;
import com.company.ems_api.repository.EmployeeRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee addEmployee(@Valid Employee employee)
    {
        return employeeRepo.save(employee);
    }
}
