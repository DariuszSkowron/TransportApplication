package pl.skowrondariusz.TransportApplication.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.skowrondariusz.TransportApplication.security.model.PasswordResetToken;
import pl.skowrondariusz.TransportApplication.security.model.User;

import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser (User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete FROM PasswordResetToken t WHERE t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

}