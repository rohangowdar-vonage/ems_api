package com.company.ems_api.repository;

import com.company.ems_api.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long>
{
    boolean existsByEmail(String email);
}
