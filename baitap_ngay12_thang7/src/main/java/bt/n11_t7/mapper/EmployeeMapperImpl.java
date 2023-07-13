package bt.n11_t7.mapper;

import bt.n11_t7.dto.EmployeeDTO;
import bt.n11_t7.model.Employee;
import bt.n11_t7.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto==null){
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee entity) {
        if (entity==null){
            return null;
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(entity.getId());
        employeeDTO.setName(entity.getName());
        employeeDTO.setEmail(entity.getEmail());
        employeeDTO.setDepartmentName(entity.getDepartment().getName());
        List<String> listRole = new ArrayList<>();
        for (Role r: entity.getRoles()) {
            listRole.add(r.getName());
        }
        employeeDTO.setRoles(listRole);
        return employeeDTO;
    }

    @Override
    public List<Employee> toEntity(List<EmployeeDTO> dtoList) {
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeDTO dto : dtoList) {
            employeeList.add(toEntity(dto));
        }
        return employeeList;
    }

    @Override
    public List<EmployeeDTO> toDto(List<Employee> entityList) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee e : entityList) {
            employeeDTOS.add(toDto(e));
        }
        return employeeDTOS;
    }
}
