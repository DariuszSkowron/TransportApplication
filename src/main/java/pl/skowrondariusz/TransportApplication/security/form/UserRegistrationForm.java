package pl.skowrondariusz.TransportApplication.security.form;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import pl.skowrondariusz.TransportApplication.security.constraint.FieldMatch;
import pl.skowrondariusz.TransportApplication.security.constraint.ValidPassword;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationForm {

    private Long userId;


    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @NotEmpty(message = "Please provide your unique  username")
    private String userName;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    @ValidPassword
    private String confirmPassword;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    private String signInProvider;
    private String providerUserId;


    @AssertTrue (message = "You have to accept terms and conditions to register account")
    private Boolean terms;


    public UserRegistrationForm() {

    }

    public UserRegistrationForm(Connection<?> connection) {
//        UserProfile socialUserProfile = connection.fetchUserProfile();
        this.userId = null;
        this.email = connection.fetchUserProfile().getEmail();
        this.confirmEmail = connection.fetchUserProfile().getEmail();
        this.userName = connection.fetchUserProfile().getUsername();
        this.firstName = connection.fetchUserProfile().getFirstName();
        this.lastName = connection.fetchUserProfile().getLastName();

        ConnectionKey key = connection.getKey();
        this.signInProvider = key.getProviderId();
        this.providerUserId = key.getProviderUserId();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
