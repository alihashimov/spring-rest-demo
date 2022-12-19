package az.et.springrest.service;


import az.et.springrest.rest.model.dto.EmployeeDto;
import az.et.springrest.rest.model.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse getAllEmployees();
    EmployeeDto getEmployee(int id);
    EmployeeResponse getEmployeeByNameAndSurname(String name ,String surname);
    void insert(EmployeeDto employeeDto);

    void update(EmployeeDto employeeDto, int id);

    void updateSome(EmployeeDto employeeDto, int id);

    void delete(int id);
}
