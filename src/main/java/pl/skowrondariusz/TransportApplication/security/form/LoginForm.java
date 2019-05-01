package pl.skowrondariusz.TransportApplication.security.form;

import javax.validation.constraints.NotEmpty;

public class LoginForm {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String password;

    public LoginForm() {
    }

    public LoginForm(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

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
