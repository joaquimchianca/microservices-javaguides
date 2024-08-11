package dev.joaquim.departmentService.service;

import dev.joaquim.departmentService.dto.DepartmentDto;
import dev.joaquim.departmentService.exception.model.DepartmentNotFoundException;
import dev.joaquim.departmentService.exception.model.SameDepartmentCodeException;
import dev.joaquim.departmentService.model.Department;
import dev.joaquim.departmentService.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(d -> modelMapper.map(d, DepartmentDto.class))
                .toList();
    }

    public String createDepartment(DepartmentDto dto) {
        Department newDepartment = modelMapper.map(dto, Department.class);

        if (newDepartment.getEmployeeCount() == null) {

            newDepartment.setEmployeeCount(0L);

        }
        // check para departamentos com mesmo código
        if (departmentRepository.existsByCode(newDepartment.getCode()))
            throw new SameDepartmentCodeException(newDepartment.getCode());

        Department response = departmentRepository.save(newDepartment);
        return response.getId();
    }

    public DepartmentDto getOne(String targetId) {
        Department targetDepartment = departmentRepository.findById(targetId)
                .orElseThrow(() -> new DepartmentNotFoundException(targetId));
        return modelMapper.map(targetDepartment, DepartmentDto.class);
    }

    public DepartmentDto updateDepartment(String id, DepartmentDto dto) {
        Department targetDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            targetDepartment.setName(dto.getName());
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            targetDepartment.setDescription(dto.getDescription());
        }

        if (dto.getCode() != null && !dto.getCode().isBlank()) {
            if (departmentRepository.existsByCode(targetDepartment.getCode()))
                throw new SameDepartmentCodeException(targetDepartment.getCode());

            targetDepartment.setCode(dto.getCode());
        }

        if (dto.getEmployeeCount() != null) {
            targetDepartment.setEmployeeCount(dto.getEmployeeCount());
        }

        Department updatedDepartment = departmentRepository.save(targetDepartment);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    public String removeDepartment(String id) {
        Department target = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentRepository.deleteById(id);
        return target.getId();
    }

    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department target = departmentRepository.findByCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentCode));

        return modelMapper.map(target, DepartmentDto.class);
    }


    public DepartmentDto sumOneToEmployeeCount(String departmentCode) {
        Department target = departmentRepository.findByCode(departmentCode)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentCode));

        Long newCount = target.getEmployeeCount() + 1;
        target.setEmployeeCount(newCount);
        Department newDepartment = departmentRepository.save(target);

        return modelMapper.map(newDepartment, DepartmentDto.class);
    }
}
