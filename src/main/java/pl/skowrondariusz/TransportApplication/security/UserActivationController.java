package pl.skowrondariusz.TransportApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/registrationConfirm")
public class UserActivationController {

    @Autowired
    private UserService userService;


    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @GetMapping
    public String displayConfirmRegistrationPage(@RequestParam(required = false) String token,
                                           Model model) {

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (verificationToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", verificationToken.getToken());
        }
        final User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveRegisteredUser(user);

        return "registrationConfirm";
    }

    @GetMapping
    public String resendRegistrationToken(Model model){
        VerificationToken newVerificationToken = verificationTokenRepository.()
        return "registrationConfirm";
    }


    @RequestMapping
    public String resendRegistrationToken(@ModelAttribute("resendRegistrationTokenForm") @Valid VerificationTokenResendDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "forgot-password";
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@skowrondariusz.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://skowrondariusz.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }



//    @GetMapping
//    public String confirmRegistration
//            (WebRequest request, Model model, @RequestParam(required = false) String token) {
//
//        Locale locale = request.getLocale();
//
//        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
//        if (verificationToken == null) {
//
//            model.addAttribute("error","Could not find password reset token." );
//            return "redirect:/badUser.html?lang=" + locale.getLanguage();
//        }
//        else if (verificationToken.isExpired()){
//            model.addAttribute("error", "Token has expired, please request a new password reset.");
//
//        }
//        final User user = verificationToken.getUser();
//        user.setEnabled(true);
//        userService.saveRegisteredUser(user);
//        return "registrationConfirm";
//    }



//    @GetMapping
//    public String displayResetPasswordPage(@RequestParam(required = false) String token,
//                                           Model model) {
//
//        PasswordResetToken resetToken = tokenRepository.findByToken(token);
//        if (resetToken == null) {
//            model.addAttribute("error", "Could not find password reset token.");
//        } else if (resetToken.isExpired()) {
//            model.addAttribute("error", "Token has expired, please request a new password reset.");
//        } else {
//            User user = resetToken.getUser();
//            user.setEnabled(true);
//
//
//        }
//        return "redirect:/login?resetSuccess";
//    }
//
//    @PostMapping
//    @Transactional
//    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
//                                      BindingResult result,
//                                      RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()){
//            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
//            redirectAttributes.addFlashAttribute("passwordResetForm", form);
//            return "redirect:/account-activation?token=" + form.getToken();
//        }
//
//        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
//        User user = token.getUser();
//        user.setEnabled(true);
//        userService.saveRegisteredUser(user);
//
//        return "redirect:/login?resetSuccess";
//    }
}

