package bt.n11_t7.service.impl;

import bt.n11_t7.model.Department;
import bt.n11_t7.repository.DepartmentRepository;
import bt.n11_t7.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }
    @Override
    public Optional<Department> findById(Long aLong) {
        return departmentRepository.findById(aLong);
    }
    @Override
    public void deleteById(Long aLong) {
        departmentRepository.deleteById(aLong);
    }
    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }
}
