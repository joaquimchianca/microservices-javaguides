package dev.joaquim.employee_service.controller;

import dev.joaquim.employee_service.dto.EmployeeDto;
import dev.joaquim.employee_service.dto.ResponseDto;
import dev.joaquim.employee_service.model.Employee;
import dev.joaquim.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // read all
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // read one
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> readOneEmployee(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.getOneEmployee(id));
    }

    // create one
    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto dto) {
        Employee response =  employeeService.createEmployee(dto);
        return new ResponseEntity<String>(response.getId(), HttpStatus.CREATED );
    }

    // update one
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable String id,
            @RequestBody EmployeeDto dto
    ) {
        return new ResponseEntity<EmployeeDto>(employeeService.updateEmployee(id, dto), HttpStatus.OK);
    }

    // get all employees from one department
    @GetMapping("/code/{departmentCode}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentCode(
            @PathVariable String departmentCode
    ) {
        return ResponseEntity.ok(employeeService.findByDepartmentCode(departmentCode));
    }

    // delete one
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
        EmployeeDto response = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(response.getId());
    }
}
