package pl.skowrondariusz.TransportApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skowrondariusz.TransportApplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);
}
