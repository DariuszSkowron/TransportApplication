package pl.skowrondariusz.TransportApplication.security;


import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

    User findByEmail(String email);


    User save(UserRegistrationDto registration);


    void updatePassword(String password, Long userId);

    void saveRegisteredUser(User user);

//    void createVerificationToken(User user, String token);
//
//    VerificationToken getVerificationToken(String VerificationToken);


}
