package dev.joaquim.departmentService.repository;

import dev.joaquim.departmentService.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByCode(String departmentCode);

    Optional<Department> findByCode(String departmentCode);
}
