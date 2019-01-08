package pl.skowrondariusz.TransportApplication.service;


import pl.skowrondariusz.TransportApplication.dto.UserDto;
import pl.skowrondariusz.TransportApplication.model.User;
import pl.skowrondariusz.TransportApplication.validation.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}
