package pl.skowrondariusz.TransportApplication.security.model;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private User user;
    private HttpServletRequest request;

    public OnRegistrationCompleteEvent(
            User user, Locale locale, String appUrl, HttpServletRequest request) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
        this.request = request;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
