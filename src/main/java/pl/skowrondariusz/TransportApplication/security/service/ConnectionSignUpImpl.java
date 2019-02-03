package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import pl.skowrondariusz.TransportApplication.security.model.User;

public class ConnectionSignUpImpl implements ConnectionSignUp {

    private UserService userService;

    public ConnectionSignUpImpl(UserService userService) {
        this.userService = userService;
    }

    // After logging in social networking.
    // This method will be called to create a corresponding App_User record
    // if it does not already exist.
    @Override
    public String execute(Connection<?> connection) {

        User account = userService.createAppUser(connection);
        return account.getUserName();
    }

}
