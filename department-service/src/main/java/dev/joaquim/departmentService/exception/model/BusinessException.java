package dev.joaquim.departmentService.exception.model;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
