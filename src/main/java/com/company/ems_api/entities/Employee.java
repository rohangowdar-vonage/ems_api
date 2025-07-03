package com.company.ems_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String emp_Name;

    @Min(value = 18,message = "Employee Minimum age should be 18 years")
    private int emp_Age;

    @DecimalMin(value = "0.0", message = "Salary must be a positive number")
    @NotNull(message = "Salary is mandatory")
    private BigDecimal emp_Sal;

    @Email(message = "Invalid Email ID")
    @NotBlank(message = "Email is mandatory")
    @Column (name= "emp_Email",unique = true,nullable = false)
    private String empEmail;

    @NotBlank(message = "Role is mandatory")
    private String emp_Role;

    //******************** Mapping ************

    //  Many Employees -> One Manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonBackReference
    private Employee manager;
    // One To Many
    @OneToMany(mappedBy = "manager" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Employee> team = new HashSet<>(); // we will get the unique employees

//  Many To One
    @ManyToOne()
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;
}
