package com.company.ems_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Manager
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Manager name is mandatory")
    private String mgr_Name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String mgr_Email;

    @OneToMany(mappedBy = "manager" ,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Employee> team;
}
