package pl.skowrondariusz.TransportApplication.security.form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

    @NotEmpty
    String userName;

    @NotEmpty
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
