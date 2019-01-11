//package pl.skowrondariusz.TransportApplication.security_old;
//
//import org.springframework.context.ApplicationEvent;
//import pl.skowrondariusz.TransportApplication.model.User;
//
//import java.util.Locale;
//
//public class OnRegistrationCompleteEvent extends ApplicationEvent {
//    private String appUrl;
//    private Locale locale;
//    private User user;
//
//    public OnRegistrationCompleteEvent(
//            User user, Locale locale, String appUrl) {
//        super(user);
//
//        this.user = user;
//        this.locale = locale;
//        this.appUrl = appUrl;
//    }
//
//    public String getAppUrl() {
//        return appUrl;
//    }
//
//    public void setAppUrl(String appUrl) {
//        this.appUrl = appUrl;
//    }
//
//    public Locale getLocale() {
//        return locale;
//    }
//
//    public void setLocale(Locale locale) {
//        this.locale = locale;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
