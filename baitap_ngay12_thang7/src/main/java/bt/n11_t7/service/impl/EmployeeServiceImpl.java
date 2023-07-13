package bt.n11_t7.service.impl;

import bt.n11_t7.model.Employee;
import bt.n11_t7.repository.EmployeeRepository;
import bt.n11_t7.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long aLong) {
        return employeeRepository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        employeeRepository.deleteById(aLong);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),5,Sort.by("name"));
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Page<Employee> findAllByName(String name, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),5,Sort.by("name"));
        return employeeRepository.findAllByName(name, pageable);
    }

    @Override
    public boolean checkEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
