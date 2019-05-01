package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.Mail;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process(mail.getEmailTemplate(), context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

//    @Async
//    public void sendEmail(Mail mail) {
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message,
//                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//
//            Context context = new Context();
//            context.setVariables(mail.getModel());
//            String html = templateEngine.process("email/email-template", context);
//
//            helper.setTo(mail.getTo());
//            helper.setText(html, true);
//            helper.setSubject(mail.getSubject());
//            helper.setFrom(mail.getFrom());
//
//            emailSender.send(message);
//        } catch (Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public void accountActivationEmail(Mail mail, VerificationToken token, UserRegistrationForm user){
//        mail.setFrom("no-reply@skowrondariusz.com");
//        mail.setTo(user.getEmail());
//        mail.setSubject("Verification token resend");
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("token", token);
//        model.put("user", user);
//        model.put("signature", "https://skowrondariusz.com");
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
//        mail.setModel(model);
//        sendEmail(mail);
//    }


    public void accountResetEmail(VerificationToken token, User user, String request){
        Mail mail = new Mail();
        mail.setFrom("no-reply@skowrondariusz.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Account reset");
        mail.setEmailTemplate("email/email-template");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://skowrondariusz.com");
        String url = request;
        model.put("resetUrl", url + "/registrationConfirm?token=" + token.getToken());
        mail.setModel(model);
        sendEmail(mail);
    }






}
