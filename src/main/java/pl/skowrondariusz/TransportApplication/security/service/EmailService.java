package pl.skowrondariusz.TransportApplication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.Mail;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private static final String THYMELEAF_BANNER_IMAGE = "../../static/images/LOGO-NEW.png";
    private static final String PNG_MIME = "image/png";


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private TokenService tokenService;

    @Async
    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            context.setVariable("imageResourceName", "imageResourceName");
            String html = templateEngine.process(mail.getEmailTemplate(), context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            helper.addInline("imageResourceName", new ClassPathResource("static/images/LOGO-NEW.png"));

            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }





    public void accountActivationEmail(VerificationToken token, User user, HttpServletRequest request){
        Mail mail = new Mail();
        mail.setFrom("no-reply@skowrondariusz.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Account activation");
        mail.setEmailTemplate("email/accountActivationEmailTemplate");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://skowrondariusz.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/registrationConfirm?token=" + token.getToken());
        mail.setModel(model);
        sendEmail(mail);
    }


    public void sendPasswordResetEmail(User user, HttpServletRequest request){
        tokenService.createPasswordResetToken(user);
        Mail mail = new Mail();
        mail.setFrom("no-reply@skowrondariusz.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");
        mail.setEmailTemplate("email/passwordResetTemplate");

        Map<String, Object> model = new HashMap<>();
        model.put("token", tokenService.getPasswordResetToken(user));
        model.put("user", user);
        model.put("signature", "https://skowrondariusz.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/registrationConfirm?token=" + tokenService.getPasswordResetToken(user));
        mail.setModel(model);
        sendEmail(mail);
    }






}
