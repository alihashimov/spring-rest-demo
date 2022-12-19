package az.et.springrest.rest.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Integer id;
    @NotBlank(message = "name is can not null")
    private String name;
    @NotBlank
    private String surname;
    private Integer age;
    private Double salary;
}
