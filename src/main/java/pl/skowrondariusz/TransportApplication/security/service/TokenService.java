package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;
import pl.skowrondariusz.TransportApplication.security.repository.UserRepository;
import pl.skowrondariusz.TransportApplication.security.repository.VerificationTokenRepository;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;


    public void createVerificationToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(30);
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken getVerificationToken(User user){
      return verificationTokenRepository.findByUser(user);
    }
}
