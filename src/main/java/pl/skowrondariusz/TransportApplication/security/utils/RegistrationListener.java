package pl.skowrondariusz.TransportApplication.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.skowrondariusz.TransportApplication.security.model.OnRegistrationCompleteEvent;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;
import pl.skowrondariusz.TransportApplication.security.repository.VerificationTokenRepository;
import pl.skowrondariusz.TransportApplication.security.service.EmailService;

import javax.validation.constraints.Email;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        verificationTokenRepository.save(token);
       emailService.accountResetEmail(token, user, event.getRequest());
    }
}
