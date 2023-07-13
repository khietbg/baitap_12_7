package bt.n11_t7.service.impl;

import bt.n11_t7.model.Role;
import bt.n11_t7.repository.RoleRepository;
import bt.n11_t7.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Role role) {
        repository.save(role);
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return repository.findById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }
}
