package com.company.ems_api.repository;

import com.company.ems_api.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department,Long>
{
    Optional<Department> findByDeptIgnoreCase(String Dept);
}
