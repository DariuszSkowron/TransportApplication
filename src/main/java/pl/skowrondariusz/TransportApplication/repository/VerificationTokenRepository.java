//package pl.skowrondariusz.TransportApplication.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import pl.skowrondariusz.TransportApplication.model.User;
//import pl.skowrondariusz.TransportApplication.model.VerificationToken;
//
//public interface VerificationTokenRepository
//        extends JpaRepository<VerificationToken, Long> {
//
//    VerificationToken findByToken(String token);
//
//    VerificationToken findByUser(User user);
//}