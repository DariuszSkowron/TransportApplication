package pl.skowrondariusz.TransportApplication.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

        VerificationToken findByToken(String token);

        VerificationToken findByUser(User user);
}
