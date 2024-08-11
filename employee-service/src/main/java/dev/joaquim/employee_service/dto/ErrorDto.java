package dev.joaquim.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDto {
    LocalDateTime timestamp;
    long status;
    String error;
    String path;

    public ErrorDto() {
        this.timestamp = LocalDateTime.now();
        status = 0;
        error = "";
        path = "";
    }
}
