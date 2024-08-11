package dev.joaquim.departmentService.controller;

import dev.joaquim.departmentService.dto.DepartmentDto;
import dev.joaquim.departmentService.dto.ResponseDto;
import dev.joaquim.departmentService.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDto departmentDto) {
        String idNewDepartment = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(idNewDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getSingleDepartment(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable String id, @RequestBody DepartmentDto dto
    ) {
        DepartmentDto response = departmentService.updateDepartment(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.removeDepartment(id));
    }

    @GetMapping("/code/{departmentCode}")
    public ResponseEntity<ResponseDto> getDepartmentAndEmployeesByCode(@PathVariable String departmentCode) {
        return ResponseEntity.ok(departmentService.getDepartmentAndEmployeesByCode(departmentCode));
    }

    @GetMapping("/code/{departmentCode}/only")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String departmentCode) {
        return ResponseEntity.ok(departmentService.getDepartmentByCode(departmentCode));
    }

    @PutMapping("/code/{departmentCode}")
    public ResponseEntity<DepartmentDto> updateEmployeeCount(
            @PathVariable String departmentCode,
            @RequestBody Long number
    ) {
        return ResponseEntity.ok(departmentService.updateEmployeeCount(departmentCode, number));
    }



}
