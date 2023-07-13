package bt.n11_t7.service;

import bt.n11_t7.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService{
    Department findByName(String name);
    List<Department> findAll();
    void save(Department department);
    Optional<Department> findById(Long id);
    void deleteById(Long id);


}
