package bt.n11_t7.controller;

import bt.n11_t7.dto.EmployeeDTO;
import bt.n11_t7.model.Employee;
import bt.n11_t7.model.Role;
import bt.n11_t7.service.DepartmentService;
import bt.n11_t7.service.EmployeeService;
import bt.n11_t7.mapper.EmployeeMapperImpl;
import bt.n11_t7.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeMapperImpl employeeMapper;
    private final EmployeeService employeeService;
    private final RoleService roleService;
    private DepartmentService departmentService;

    public EmployeeController(EmployeeMapperImpl employeeMapper, EmployeeService employeeService, RoleService roleService, DepartmentService departmentService) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
        this.roleService = roleService;
        this.departmentService = departmentService;
    }

    @GetMapping("/employee")
    public ResponseEntity<?> findAll(@RequestParam(value = "name", required = false) String name, Pageable pageable) {
        Page<Employee> employees;
        if (name != null && !name.isEmpty()) {
            employees = employeeService.findAllByName(name, pageable);
        } else {
            employees = employeeService.findAll(pageable);
        }
        Page<EmployeeDTO> employeeDTOS = employees.map(employeeMapper::toDto);
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id).get();
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) {
        if (employeeService.checkEmail(employeeDTO.getEmail())) {
            return new ResponseEntity<>("email existed, please try again!", HttpStatus.FAILED_DEPENDENCY);
        }
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee.setDepartment(departmentService.findByName(employeeDTO.getDepartmentName()));
        Set<Role> roles = new HashSet<>();
        if (employeeDTO.getRoles() == null || employeeDTO.getRoles().isEmpty()) {
            Role role = roleService.findByName("ROlE_USER")
                    .orElseThrow(() -> new RuntimeException("NOT FOUND ROLE"));
            roles.add(role);
        } else {
            employeeDTO.getRoles().forEach(role -> {
                switch (role) {
                    case "ROLE_USER":
                        Role adminRole = roleService.findByName("ROLE_USER")
                                .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                        roles.add(adminRole);
                    case "ROLE_ADMIN":
                        Role userRole = roleService.findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                        roles.add(userRole);
                }
            });
        }
        employee.setRoles(roles);
        employeeService.save(employee);
        return new ResponseEntity<>("create success fully! ", HttpStatus.CREATED);
    }

    @PutMapping("/employee")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employee) {
        Optional<Employee> employeeByEmail = employeeService.findByEmail(employee.getEmail());
        if (employeeByEmail.isPresent() && employeeByEmail.get().getId() != employee.getId()) {
            return new ResponseEntity<>("email existed, please try again! ", HttpStatus.BAD_REQUEST);
        }
        Optional<Employee> employeeUpdate = employeeService.findById(employee.getId());
        if (employeeUpdate.isPresent()) {
            Employee employee1 = employeeUpdate.get();
            employee1.setDepartment(departmentService.findByName(employee.getDepartmentName()));
            employee1.setName(employee.getName());
            employee1.setEmail(employee.getEmail());
            if (employee.getRoles() == null || employee.getRoles().isEmpty()) {
                employee1.setRoles(employee1.getRoles());
            } else {
                Set<Role> listRole = new HashSet<>();
                employee.getRoles().forEach(role -> {
                    switch (role) {
                        case "ROLE_USER":
                            Role adminRole = roleService.findByName("ROLE_USER")
                                    .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                            listRole.add(adminRole);
                        case "ROLE_ADMIN":
                            Role userRole = roleService.findByName("ROLE_ADMIN")
                                    .orElseThrow(() -> new RuntimeException("Failed -> NOT FOUND ROLE"));
                            listRole.add(userRole);
                    }
                });
                employee1.setRoles(listRole);
            }

            employeeService.save(employee1);
        }
        return new ResponseEntity<>("update success fully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (!employee.isPresent()) {
            return new ResponseEntity<>("not fuond, please try again! ", HttpStatus.NOT_FOUND);
        } else {
            employeeService.deleteById(id);
            return new ResponseEntity<>("delete success fully!", HttpStatus.OK);
        }
    }

}
