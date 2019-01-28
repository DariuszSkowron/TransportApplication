package pl.skowrondariusz.TransportApplication.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.skowrondariusz.TransportApplication.security.form.PasswordForgotForm;
import pl.skowrondariusz.TransportApplication.security.form.PasswordResetForm;
import pl.skowrondariusz.TransportApplication.security.model.PasswordResetToken;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.repository.PasswordResetTokenRepository;
import pl.skowrondariusz.TransportApplication.security.service.UserService;
import pl.skowrondariusz.TransportApplication.security.utils.EncrytedPasswordUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {
    private Logger logger;
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetController.class);

    @Autowired
    private UserService userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
//    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetForm passwordReset() {
        return new PasswordResetForm();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "reset-password";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetForm form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(form.getPassword());
        String updatedPassword = ("{bcrypt}" +encrytedPassword);
        userService.updatePassword(updatedPassword, user.getUserId());
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }

}

