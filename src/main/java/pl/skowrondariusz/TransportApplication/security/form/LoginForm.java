package pl.skowrondariusz.TransportApplication.security.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

    @Autowired
    private MessageSource messages;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public LoginForm() {
    }

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
