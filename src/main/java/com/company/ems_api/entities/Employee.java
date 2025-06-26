package com.company.ems_api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

//@Data
@Getter
@Setter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonBackReference
    private Manager manager;

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Manager))
        {
            return false;
        }
        Manager that = (Manager) obj;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

}
