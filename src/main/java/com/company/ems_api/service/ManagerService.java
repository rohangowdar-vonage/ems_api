package com.company.ems_api.service;

import com.company.ems_api.entities.Manager;
import com.company.ems_api.repository.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService
{
    @Autowired
    private ManagerRepo managerRepo;

    public Manager addManager(Manager manager) {
        if (managerRepo.existsByMgrEmail(manager.getMgrEmail())) {
            throw new IllegalArgumentException("Manager with email " + manager.getMgrEmail() + " already exists.");
        }
        return managerRepo.save(manager);
    }

    public Optional<Manager> findById(Long id)
    {
        return managerRepo.findById(id);
    }
}

