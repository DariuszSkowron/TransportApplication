package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skowrondariusz.TransportApplication.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}