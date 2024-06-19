package dev.joaquim.departmentService.repository;

import dev.joaquim.departmentService.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
}
