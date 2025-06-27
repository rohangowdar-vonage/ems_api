package com.company.ems_api.controller;

import com.company.ems_api.entities.Employee;
import com.company.ems_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save_emp")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee)
    {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    @GetMapping("/emp_get")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
    {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee emp)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(id, emp));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable Long id, @RequestBody Employee partial)
    {
        return ResponseEntity.ok(employeeService.patchEmployee(id, partial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id)
    {
        employeeService.deleteEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }
}
