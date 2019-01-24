package pl.skowrondariusz.TransportApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skowrondariusz.TransportApplication.security_2.dao.AppUserDAO;
import pl.skowrondariusz.TransportApplication.security_2.entity.AppUser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller

public class UserActivationController {
    @Autowired
    private AppUserDAO appUserDAO;
    @Autowired
    private EmailService emailService;

    @ModelAttribute("resendRegistrationTokenForm")
    public VerificationTokenResendForm verificationTokenResendDto() {
        return new VerificationTokenResendForm();
    }

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @RequestMapping( value ="/registrationConfirm", method = RequestMethod.GET)
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
        final AppUser user = verificationToken.getUser();
        user.setEnabled(true);
        appUserDAO.saveRegisteredUser(user);

        return "registrationConfirm";
    }

    @RequestMapping(value = "/resendToken", method = RequestMethod.GET)
    public String displayForgotPasswordPage() {
        return "resendToken";
    }


    @RequestMapping(value = "/resendToken", method = RequestMethod.POST)
    public String resendRegistrationToken(@ModelAttribute("resendRegistrationTokenForm") @Valid VerificationTokenResendForm form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()){
            return "resendToken";
        }

        AppUser user = appUserDAO.findByEmail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "resendToken";
        }

        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        verificationTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@skowrondariusz.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Verification token resend");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://skowrondariusz.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/registrationConfirm?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/resendToken?success";

    }
//    forgot-password



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
//    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetForm form,
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

