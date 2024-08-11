package dev.joaquim.departmentService.service;

import dev.joaquim.departmentService.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8081", name = "employee-service")
public interface APIClient {

    @GetMapping("/v1/employee/code/{departmentCode}")
    List<EmployeeDto> getEmployeesByDepartmentCode(@PathVariable String departmentCode);
}
