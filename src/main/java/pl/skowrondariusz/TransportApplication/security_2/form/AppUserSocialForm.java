package pl.skowrondariusz.TransportApplication.security_2.form;

import pl.skowrondariusz.TransportApplication.security.ValidPassword;

public class AppUserSocialForm {

    private Long userId;
    private String email;
    private String userName;

    private String firstName;
    private String lastName;

    @ValidPassword
    private String password;
    private String role;
    private String signInProvider;
    private String providerUserId;


    private Boolean terms;


//    public AppUserForm() {
//
//    }
//
//    public AppUserForm(Connection<?> connection) {
//        UserProfile socialUserProfile = connection.fetchUserProfile();
//        this.userId = null;
//        this.email = socialUserProfile.getEmail();
//        this.userName = socialUserProfile.getUsername();
//        this.firstName = socialUserProfile.getFirstName();
//        this.lastName = socialUserProfile.getLastName();
//
//
//        ConnectionKey key = connection.getKey();
//        this.signInProvider = key.getProviderId();
//        this.providerUserId = key.getProviderUserId();
//    }
//
//
//    public AppUserForm(String email, String userName, String firstName, String lastName, String password, String role, Boolean terms) {
//        this.email = email;
//        this.userName = userName;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.password = password;
//        this.role = role;
//        this.terms = terms;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

}