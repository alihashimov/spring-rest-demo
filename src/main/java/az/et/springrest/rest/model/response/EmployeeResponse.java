package az.et.springrest.rest.model.response;

import az.et.springrest.rest.model.dto.EmployeeDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private List<EmployeeDto> employees;
}
