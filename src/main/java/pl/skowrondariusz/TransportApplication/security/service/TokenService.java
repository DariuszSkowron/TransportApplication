package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.security.model.PasswordResetToken;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;
import pl.skowrondariusz.TransportApplication.security.repository.PasswordResetTokenRepository;
import pl.skowrondariusz.TransportApplication.security.repository.UserRepository;
import pl.skowrondariusz.TransportApplication.security.repository.VerificationTokenRepository;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public void createVerificationToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(30);
        verificationTokenRepository.save(verificationToken);
    }

    public void createPasswordResetToken(User user){
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(30);
        passwordResetTokenRepository.save(passwordResetToken);
    }


    public VerificationToken getVerificationToken(User user){
      return verificationTokenRepository.findByUser(user);
    }

    public PasswordResetToken getPasswordResetToken(User user){
        return passwordResetTokenRepository.findByUser(user);
    }
}
