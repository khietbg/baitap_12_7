package bt.n11_t7.repository;

import bt.n11_t7.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findAllByName(String name, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);
    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
}
