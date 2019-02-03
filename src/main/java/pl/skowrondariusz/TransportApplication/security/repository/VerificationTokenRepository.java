package pl.skowrondariusz.TransportApplication.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

        VerificationToken findByToken(String token);

        VerificationToken findByUser(User user);
}
