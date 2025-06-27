package com.company.ems_api.repository;

import com.company.ems_api.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepo extends JpaRepository<Manager,Long>
{
    boolean existsByEmail(String email);
}
