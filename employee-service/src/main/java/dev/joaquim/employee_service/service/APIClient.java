package dev.joaquim.employee_service.service;

import dev.joaquim.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "http://localhost:8082", value = "department-service")
public interface APIClient {

    @GetMapping("/v1/department/code/{departmentCode}")
    DepartmentDto getDepartmentByCode(@PathVariable String departmentCode);

    @PutMapping("/v1/department/code/{departmentCode}")
    void sumOneToEmployeeCount(@PathVariable String departmentCode);

}
