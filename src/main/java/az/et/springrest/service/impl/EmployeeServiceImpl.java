package az.et.springrest.service.impl;

import az.et.springrest.exception.CustomNotFoundException;
import az.et.springrest.model.Employee;
import az.et.springrest.repository.EmployeeRepository;
import az.et.springrest.rest.model.dto.EmployeeDto;
import az.et.springrest.rest.model.response.EmployeeResponse;
import az.et.springrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.et.springrest.enums.ErrorCodeEnum.EMPLOYEE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse getAllEmployees() {

        List<EmployeeDto> employeeDtoList = employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return makeEmployeeResponse(employeeDtoList);
    }

    @Override
    public EmployeeDto getEmployee(int id) {
        return employeeRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new CustomNotFoundException(EMPLOYEE_NOT_FOUND));
    }

    @Override
    public EmployeeResponse getEmployeeByNameAndSurname(String name, String surname) {
        List<EmployeeDto> employeeDtoList = employeeRepository.findByNameAndSurname(name, surname)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return makeEmployeeResponse(employeeDtoList);
    }

    @Override
    public void insert(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        employeeRepository.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto, int id) {
        Employee employee = getEmployeeById(id);
        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void updateSome(EmployeeDto employeeDto, int id) {
        Employee employee = getEmployeeById(id);
        if (employeeDto.getName() != null)
            employee.setName(employeeDto.getName());
        if (employeeDto.getSurname() != null)
            employee.setSurname(employeeDto.getSurname());
        if (employeeDto.getAge() != null)
            employee.setAge(employeeDto.getAge());
        if (employeeDto.getSalary() != null)
            employee.setSalary(employeeDto.getSalary());
        employeeRepository.save(employee);
    }

    @Override
    public void delete(int id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    private Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(EMPLOYEE_NOT_FOUND));

    }

    private EmployeeResponse makeEmployeeResponse(List<EmployeeDto> employeeDtoList) {
        return EmployeeResponse.builder()
                .employees(employeeDtoList)
                .build();
    }

    private EmployeeDto convertToDto(Employee employee) {
//        return EmployeeDto.builder()
//                .id(employee.getId())
//                .name(employee.getName())
//                .surname(employee.getSurname())
//                .age(employee.getAge())
//                .salary(employee.getSalary())
//                .build();
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee, employeeDto);
        return employeeDto;
    }
}
