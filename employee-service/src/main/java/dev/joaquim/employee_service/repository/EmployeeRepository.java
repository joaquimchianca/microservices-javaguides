package dev.joaquim.employee_service.repository;

import dev.joaquim.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findByDepartmentCode(String departmentCode);

}
