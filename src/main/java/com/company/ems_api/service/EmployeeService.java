package com.company.ems_api.service;

import com.company.ems_api.entities.Employee;
import com.company.ems_api.exception.CustomException;
import com.company.ems_api.exception.DuplicateEmailException;
import com.company.ems_api.repository.EmployeeRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee addEmployee(@Valid Employee employee)
    {
        String email = employee.getEmpEmail();
        employee.setEmpEmail(email);

        if (employeeRepo.existsByEmpEmailIgnoreCase(email)) {
            throw new DuplicateEmailException("Employee with email " + employee.getEmpEmail()+ " already exists.");
        }
        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployee()
    {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id)
    {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, @Valid Employee eDetails)
    {
        Employee employee = getEmployeeById(id);
        employee.setEmp_Name(eDetails.getEmp_Name());
        employee.setEmp_Age(eDetails.getEmp_Age());
        employee.setEmp_Sal(eDetails.getEmp_Sal());
        employee.setEmpEmail(eDetails.getEmpEmail());
        employee.setEmp_Role(eDetails.getEmp_Role());
        employee.setManager(eDetails.getManager());
        return employeeRepo.save(employee);
    }

    public Employee patchEmployee(Long id, Employee partial)
    {
        Employee emp = getEmployeeById(id);
        if (partial.getEmp_Name() != null)
        {
            emp.setEmp_Name(partial.getEmp_Name());
        }
        if (partial.getEmp_Age() != 0)
        {
            emp.setEmp_Age(partial.getEmp_Age());
        }
        if (partial.getEmp_Sal() != null)
        {
            emp.setEmp_Sal(partial.getEmp_Sal());
        }
        if (partial.getEmpEmail() != null)
        {
            emp.setEmpEmail(partial.getEmpEmail());
        }
        if (partial.getEmp_Role() != null)
        {
            emp.setEmp_Role(partial.getEmp_Role());
        }
        if (partial.getManager() != null)
        {
            emp.setManager(partial.getManager());
        }
        return employeeRepo.save(emp);
    }

    public void deleteEmployee(Long id)
    {
        employeeRepo.deleteById(id);
    }
}
