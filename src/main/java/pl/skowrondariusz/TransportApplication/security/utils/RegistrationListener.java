package pl.skowrondariusz.TransportApplication.security.utils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationListener;

import org.springframework.stereotype.Component;

import pl.skowrondariusz.TransportApplication.security.model.OnRegistrationCompleteEvent;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.repository.VerificationTokenRepository;
import pl.skowrondariusz.TransportApplication.security.service.EmailService;
import pl.skowrondariusz.TransportApplication.security.service.TokenService;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
       tokenService.createVerificationToken(event.getUser());
       emailService.accountActivationEmail(tokenService.getVerificationToken(user), user, event.getRequest());
    }
}
