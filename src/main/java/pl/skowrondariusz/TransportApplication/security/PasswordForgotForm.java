package pl.skowrondariusz.TransportApplication.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PasswordForgotForm {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @ValidReCaptcha
    private String reCaptchaResponse;

    public String getReCaptchaResponse() {
        return reCaptchaResponse;
    }

    public void setReCaptchaResponse(String reCaptchaResponse) {
        this.reCaptchaResponse = reCaptchaResponse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}