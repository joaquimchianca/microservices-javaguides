package dev.joaquim.employee_service.service;

import dev.joaquim.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "department-service")
public interface APIClient {

    @GetMapping("/v1/department/code/{departmentCode}/only")
    DepartmentDto getDepartmentByCode(@PathVariable String departmentCode);

    @PutMapping("/v1/department/code/{departmentCode}")
    void updateEmployeeCount(@PathVariable String departmentCode, @RequestBody Long number);

}
