package dev.joaquim.departmentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
