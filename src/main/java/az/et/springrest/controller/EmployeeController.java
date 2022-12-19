package az.et.springrest.controller;

import az.et.springrest.rest.model.dto.EmployeeDto;
import az.et.springrest.rest.model.response.EmployeeResponse;
import az.et.springrest.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
@Tag(name = "Employee Services",description = "employee services")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public EmployeeResponse getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employee-id}")
    @Operation(summary = "This gets employee by id")
    public EmployeeDto getEmployee(@PathVariable("employee-id") int employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @GetMapping("/search")
    public EmployeeResponse getEmployeeByNameAndSurname(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname) {
        return employeeService.getEmployeeByNameAndSurname(name, surname);

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid EmployeeDto employeeDto) {
        employeeService.insert(employeeDto);
    }
    @PutMapping("/{id}")
    public void updateAll(@RequestBody EmployeeDto employeeDto,@PathVariable("id") int id){
        employeeService.update(employeeDto,id);
    }
    @PatchMapping("/{id}")
    public void updateSome(@RequestBody EmployeeDto employeeDto,@PathVariable("id") int id){
        employeeService.updateSome(employeeDto,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        employeeService.delete(id);

    }
}
