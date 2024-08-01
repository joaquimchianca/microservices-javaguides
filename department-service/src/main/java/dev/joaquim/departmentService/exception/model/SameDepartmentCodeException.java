package dev.joaquim.departmentService.exception.model;

public class SameDepartmentCodeException extends RuntimeException {
    public SameDepartmentCodeException(String departmentCode) {
        super("Já existe um departamento com esse código : " + departmentCode);
    }
}
