package com.company.ems_api.controller;

import com.company.ems_api.entities.Manager;
import com.company.ems_api.repository.ManagerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mgr")
public class ManagerController
{
    @Autowired
    private ManagerRepo managerrepo;

    @PostMapping
    public ResponseEntity<Manager> createMagnager(@Valid @RequestBody Manager manager)
    {
        if(manager.getTeam() != null)
        {
            manager.getTeam().forEach(employee -> employee.setManager(manager));
        }
        return ResponseEntity.ok(managerrepo.save(manager));
    }

    @GetMapping("/{id}/team")
    public ResponseEntity<Manager> getManagerWithTeam(@PathVariable Long id) {
        Manager manager = managerrepo.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        return ResponseEntity.ok(manager);
    }
}
