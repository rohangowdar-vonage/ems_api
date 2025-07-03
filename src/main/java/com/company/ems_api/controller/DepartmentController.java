package com.company.ems_api.controller;

import com.company.ems_api.entities.Department;
import com.company.ems_api.repository.DepartmentRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController
{
    @Autowired
    private DepartmentRepo departmentRepo;

    @PostMapping("/add")
    private ResponseEntity<?> addDepartment(@Valid @RequestBody Department dept)
    {
        String normalizedName = dept.getDept().trim();

        // Check if it exists
        boolean exists = departmentRepo.findByDeptIgnoreCase(normalizedName).isPresent();
        if (exists) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", 400,
                    "error", "Bad Request",
                    "message", "Department already exists: " + normalizedName
            ));
        }

        // Save new department
        dept.setDept(normalizedName); // trim and save clean
        Department saved = departmentRepo.save(dept);

        return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "Department created successfully",
                "department", saved
        ));
    }
}
