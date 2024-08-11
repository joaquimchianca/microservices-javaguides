package dev.joaquim.employee_service.service;

import dev.joaquim.employee_service.dto.DepartmentDto;
import dev.joaquim.employee_service.dto.EmployeeDto;
import dev.joaquim.employee_service.dto.ResponseDto;
import dev.joaquim.employee_service.model.Employee;
import dev.joaquim.employee_service.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final APIClient apiClient;


    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(e -> modelMapper.map(e,
                EmployeeDto.class)).toList();
    }


    public ResponseDto getOneEmployee(String id) {
        Employee target = employeeRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException());

        DepartmentDto departmentDto = apiClient.getDepartmentByCode(target.getDepartmentCode());
        EmployeeDto employeeDto = modelMapper.map(target, EmployeeDto.class);
        return new ResponseDto(employeeDto, departmentDto);
    }

    public Employee createEmployee(EmployeeDto dto) {

        try {
            apiClient.getDepartmentByCode(dto.getDepartmentCode());
        } catch (Exception ex) {
            throw new EntityNotFoundException();
        }

        apiClient.sumOneToEmployeeCount(dto.getDepartmentCode());

        return employeeRepository.save(
                modelMapper.map(dto, Employee.class)
        );

    }


    public EmployeeDto updateEmployee(String id, EmployeeDto dto) {
        Employee target = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException());

        if (isNotBlank(dto.getFirstName())) {
            target.setFirstName(dto.getFirstName());
        }

        if (isNotBlank(dto.getLastName())) {
            target.setLastName(dto.getLastName());
        }

        if (isNotBlank(dto.getEmail())) {
            target.setEmail((dto.getEmail()));
        }

        Employee newEmployee = employeeRepository.save(target);
        return modelMapper.map(newEmployee, EmployeeDto.class);
    }

    // checa se valor pode ser atualizado
    private boolean isNotBlank(String str) {
        return str != null && !str.isBlank();
    }

    public EmployeeDto deleteEmployee(String id) {
        Employee target = employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException());

        employeeRepository.deleteById(id);
        return modelMapper.map(target, EmployeeDto.class);
    }
}
