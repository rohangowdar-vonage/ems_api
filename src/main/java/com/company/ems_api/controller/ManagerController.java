package com.company.ems_api.controller;

import com.company.ems_api.entities.Manager;
import com.company.ems_api.repository.ManagerRepo;
import com.company.ems_api.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mgr")
public class ManagerController
{
    @Autowired
    private ManagerService managerService;

    @PostMapping
    public ResponseEntity<?> createMagnager(@Valid @RequestBody Manager manager)
    {
        if(manager.getTeam() != null)
        {
            manager.getTeam().forEach(employee -> employee.setManager(manager));
        }
        try {
        Manager saved = managerService.addManager(manager);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/team")
    public ResponseEntity<Manager> getManagerWithTeam(@PathVariable Long id) {
        Manager manager = managerService.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        return ResponseEntity.ok(manager);
    }
}
