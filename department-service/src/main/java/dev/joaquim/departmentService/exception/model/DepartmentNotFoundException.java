package dev.joaquim.departmentService.exception.model;


public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String id) {
        super("Departamento não encontrado com o id : " + id);
    }
}
