package com.company.ems_api.controller;

import com.company.ems_api.entities.Department;
import com.company.ems_api.entities.Employee;
import com.company.ems_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addEmployee(@Valid @RequestBody Employee employee)
    {
        Employee saved = employeeService.addEmployee(employee);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee added successfully");
        response.put("employee", saved);

        URI path = URI.create("/api/employee/add/" + saved.getId());

        Map<String,Object> msg= new HashMap<>();
        msg.put("message", "Employee added successfully");
        msg.put("employee",saved);
        return ResponseEntity.created(path).body(msg);
    }

    @GetMapping("/fetchall")
    public ResponseEntity<List<Employee>> getAllEmployee()
    {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
    {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    // To get employees under manager
    @GetMapping
    public ResponseEntity<Set<Employee>> getTeamByManagerById(@PathVariable Long id)
    {
        Employee manager = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(manager.getTeam());
    }

    // To get the department of employees with their id
    @GetMapping("/{id}/department")
    public ResponseEntity<Department> getEmployeeDepartment(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee.getDepartment());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id, @Valid @RequestBody Employee emp)
    {
        return ResponseEntity.ok(employeeService.updateEmployee(id, emp));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchEmployeeById(@PathVariable Long id, @RequestBody Employee partial)
    {
        return ResponseEntity.ok(employeeService.patchEmployee(id, partial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployeeById(@PathVariable Long id)
    {
        employeeService.deleteEmployee(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }
}
