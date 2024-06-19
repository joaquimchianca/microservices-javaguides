package dev.joaquim.departmentService.dto;

public record DepartmentDto(
        String id,
        String name,
        String code,
        String description,
        Long employeeCount
        ) {
}
