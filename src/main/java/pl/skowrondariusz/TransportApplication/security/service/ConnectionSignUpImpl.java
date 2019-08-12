package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import pl.skowrondariusz.TransportApplication.security.model.User;

public class ConnectionSignUpImpl implements ConnectionSignUp {

    private UserService userService;

    public ConnectionSignUpImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(Connection<?> connection) {

        User account = userService.createAppUser(connection);
        return account.getUserName();
    }

}
