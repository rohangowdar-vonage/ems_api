package com.company.ems_api.controller;

import com.company.ems_api.entities.Manager;
import com.company.ems_api.repository.ManagerRepo;
import com.company.ems_api.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ManagerController
{
    @Autowired
    private ManagerService managerService;

    @PostMapping("/add_mgr")
    public ResponseEntity<Map<String,Object>> createManager(@Valid @RequestBody Manager manager)
    {
        if(manager.getTeam() != null)
        {
            manager.getTeam().forEach(employee -> employee.setManager(manager));
        }
        try
        {
            Manager saved = managerService.addManager(manager);
            URI uri = URI.create("api/add_mgr" + saved.getId());
            Map<String,Object> response =  new HashMap<>();
            response.put("message","Manager added successfully");
            response.put("message",saved);
            return ResponseEntity.created(uri).body(response);
        }
        catch (IllegalArgumentException e)
        {
            Map<String,Object> error = new HashMap<>();
            error.put("Error",e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/{id}/team")
    public ResponseEntity<Manager> getManagerWithTeam(@PathVariable Long id)
    {
        Manager manager = managerService.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        return ResponseEntity.ok(manager);
    }
}
