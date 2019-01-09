package pl.skowrondariusz.TransportApplication.service;


import pl.skowrondariusz.TransportApplication.dto.UserDto;
import pl.skowrondariusz.TransportApplication.model.User;
import pl.skowrondariusz.TransportApplication.model.VerificationToken;
import pl.skowrondariusz.TransportApplication.validation.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}