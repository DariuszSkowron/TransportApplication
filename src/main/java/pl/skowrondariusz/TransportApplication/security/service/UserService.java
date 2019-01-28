package pl.skowrondariusz.TransportApplication.security.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationForm registration);

    void updatePassword(String password, Long userId);

    void saveRegisteredUser(User user);

//    void createVerificationToken(User user, String token);
//
//    VerificationToken getVerificationToken(String VerificationToken);

    User registerNewUserAccount(UserRegistrationForm registration);

    User createAppUser(Connection<?> connection);


    String findAvailableUserName(String userName_prefix);


    User findByUserId(Long userId);

}

