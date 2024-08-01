package dev.joaquim.departmentService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "department_tb")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "department_name")
    private String name;

    @Column(name = "department_code", unique = true)
    private String code;

    @Column(name = "department_description", columnDefinition = "TEXT")
    private String description;

    private Long employeeCount;
}