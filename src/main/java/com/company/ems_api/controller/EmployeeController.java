package com.company.ems_api.controller;

import com.company.ems_api.entities.Employee;
import com.company.ems_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save_emp")
    public ResponseEntity<Map<String, Object>> addEmployee(@Valid @RequestBody Employee employee)
    {
        Employee saved = employeeService.addEmployee(employee);

        URI path = URI.create("/api/save_emp/" + saved.getId());

        employeeService.addEmployee(employee);
        Map<String,Object> msg= new HashMap<>();
        msg.put("message", "Employee added successfully");
        msg.put("employee",saved);
        return ResponseEntity.created(path).body(msg);
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
