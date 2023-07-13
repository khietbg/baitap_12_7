package bt.n11_t7.service;

import bt.n11_t7.model.Role;

import java.util.Optional;

public interface RoleService{
    void save(Role role);
    Optional<Role> findById(Long id);
    void deleteById(Long id);
    Optional<Role> findByName(String name);

}
