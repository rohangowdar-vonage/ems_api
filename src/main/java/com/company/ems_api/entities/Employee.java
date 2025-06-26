package com.company.ems_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String emp_Name;

    @Min(value = 18,message = "Minimum age is 18 years")
    private int emp_Age;

    @DecimalMin(value = "0.0", message = "Salary must be a positive number")
    @NotNull(message = "Salary is mandatory")
    private BigDecimal emp_Sal;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String emp_Email;

    @NotBlank(message = "Role is mandatory")
    private String emp_Role;

    //******************** Mapping ************
    //  Many Employees -> One Manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
}
