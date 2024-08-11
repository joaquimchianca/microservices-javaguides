package dev.joaquim.employee_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "employee_tb")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "emp_first_name")
    private String firstName;

    @Column(nullable = false, name = "emp_last_name")
    private String lastName;

    @Column(nullable = false, name = "emp_email", unique = true)
    private String email;

    @Column(name = "emp_department_code")
    private String departmentCode;
}
