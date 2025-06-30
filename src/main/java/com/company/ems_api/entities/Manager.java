package com.company.ems_api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Manager
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Manager name is mandatory")
    private String mgr_Name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    private String mgrEmail;

    @OneToMany(mappedBy = "manager" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Employee> team = new HashSet<>(); // we will get the unique employees

}
