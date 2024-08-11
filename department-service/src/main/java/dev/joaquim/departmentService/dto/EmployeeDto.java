package dev.joaquim.departmentService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    String id;
    String firstName;
    String lastName;
    String email;
    String departmentCode;
}
