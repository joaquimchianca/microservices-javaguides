package dev.joaquim.departmentService.exception;

import dev.joaquim.departmentService.exception.model.BusinessException;
import dev.joaquim.departmentService.exception.model.DepartmentNotFoundException;
import dev.joaquim.departmentService.exception.model.SameDepartmentCodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DepartmentControllerAdvice {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(SameDepartmentCodeException.class)
    public ResponseEntity<String> handleSameDepartmentCodeException(SameDepartmentCodeException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}
