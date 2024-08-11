package dev.joaquim.employee_service.repository;

import dev.joaquim.employee_service.model.Employee;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Testa inserção de entidade no bancod de dados e checa se foi adicionado.")
    public void shouldSaveAndRetrieveEmployee() {

        Employee employee = new Employee();
        employee.setFirstName("Joaquim");
        employee.setLastName("Silva");
        employee.setEmail("joaquim.silva@example.com");

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("Joaquim");
        assertThat(savedEmployee.getLastName()).isEqualTo("Silva");
        assertThat(savedEmployee.getEmail()).isEqualTo("joaquim.silva@example.com");

        Employee retrievedEmployee = employeeRepository.findById(savedEmployee.getId()).orElse(null);

        assertThat(retrievedEmployee).isNotNull();
        assertThat(retrievedEmployee.getFirstName()).isEqualTo("Joaquim");
        assertThat(retrievedEmployee.getLastName()).isEqualTo("Silva");
        assertThat(retrievedEmployee.getEmail()).isEqualTo("joaquim.silva@example.com");
    }

    @Test
    @DisplayName("Testa método de listagem de funcionários a partir do código de departamento")
    public void testFindByDepartmentCode() {

        employeeRepository.deleteAll();

        Employee emp1 = new Employee();
        emp1.setFirstName("Teste");
        emp1.setLastName("da Silva");
        emp1.setEmail("testedasilva@gmail.com");
        emp1.setDepartmentCode("Test");

        Employee emp2 = new Employee();
        emp2.setFirstName("Teste2");
        emp2.setLastName("da Silva");
        emp2.setEmail("teste2dasilva@gmail.com");
        emp2.setDepartmentCode("Test");

        employeeRepository.saveAll(Arrays.asList(emp1, emp2));

        List<Employee> employeesTest = employeeRepository.findByDepartmentCode("Test");

        assertThat(employeesTest).hasSize(2);
        assertThat(employeesTest).extracting(Employee::getFirstName)
                .containsExactlyInAnyOrder("Teste", "Teste2");
    }
}