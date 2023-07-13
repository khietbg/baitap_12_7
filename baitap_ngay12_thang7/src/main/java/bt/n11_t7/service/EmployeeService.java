package bt.n11_t7.service;

import bt.n11_t7.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService{
    List<Employee> findAll();
    void save(Employee employee);
    Optional<Employee> findById(Long id);
    void deleteById(Long id);
    Page<Employee> findAll(Pageable pageable);
    Page<Employee> findAllByName(String name, Pageable pageable);
    boolean checkEmail(String email);
    Optional<Employee> findByEmail(String email);
}
