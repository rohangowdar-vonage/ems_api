package com.company.ems_api.service;

import com.company.ems_api.entities.Department;
import com.company.ems_api.entities.Employee;
import com.company.ems_api.exception.DuplicateEmailException;
import com.company.ems_api.repository.DepartmentRepo;
import com.company.ems_api.repository.EmployeeRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public Employee addEmployee(@Valid Employee employee)
    {
        String email = employee.getEmpEmail();
        employee.setEmpEmail(email);

        if (employeeRepo.existsByEmpEmailIgnoreCase(email)) {
            throw new DuplicateEmailException("Employee with email " + employee.getEmpEmail()+ " already exists.");
        }

        Department inputDept = employee.getDepartment();
        if (inputDept != null)
        {
            // This will only allow existing department
            Department existing = departmentRepo.findByDeptIgnoreCase(inputDept.getDept())
                    .orElseThrow(() -> new IllegalArgumentException("Department does not exist: " + inputDept.getDept()));
            employee.setDepartment(existing);
        } else
        {
            throw new IllegalArgumentException("Department is required");
        }

        // Assign manager if team present
        if (employee.getTeam() != null) {
            employee.getTeam().forEach(e -> e.setManager(employee));
        }

        return employeeRepo.save(employee);
    }

    public List<Employee> getAllEmployee()
    {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id)
    {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, @Valid Employee eDetails)
    {
        Employee employee = getEmployeeById(id);
        employee.setEmp_Name(eDetails.getEmp_Name());
        employee.setEmp_Age(eDetails.getEmp_Age());
        employee.setEmp_Sal(eDetails.getEmp_Sal());
        employee.setEmpEmail(eDetails.getEmpEmail());
        employee.setEmp_Role(eDetails.getEmp_Role());
        employee.setManager(eDetails.getManager());
        return employeeRepo.save(employee);
    }

    public Employee patchEmployee(Long id, Employee partial)
    {
        Employee emp = getEmployeeById(id);
        if (partial.getEmp_Name() != null)
        {
            emp.setEmp_Name(partial.getEmp_Name());
        }
        if (partial.getEmp_Age() != 0)
        {
            emp.setEmp_Age(partial.getEmp_Age());
        }
        if (partial.getEmp_Sal() != null)
        {
            emp.setEmp_Sal(partial.getEmp_Sal());
        }
        if (partial.getEmpEmail() != null)
        {
            emp.setEmpEmail(partial.getEmpEmail());
        }
        if (partial.getEmp_Role() != null)
        {
            emp.setEmp_Role(partial.getEmp_Role());
        }
        if (partial.getManager() != null)
        {
            emp.setManager(partial.getManager());
        }
        return employeeRepo.save(emp);
    }

    public void deleteEmployee(Long id)
    {
        employeeRepo.deleteById(id);
    }
}
