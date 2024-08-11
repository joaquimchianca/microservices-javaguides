package dev.joaquim.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class DepartmentDto {
    String id;
    String name;
    String code;
    String description;
    Long employeeCount;
}
