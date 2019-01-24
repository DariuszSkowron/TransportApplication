package pl.skowrondariusz.TransportApplication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.skowrondariusz.TransportApplication.security_2.dao.AppUserDAO;
import pl.skowrondariusz.TransportApplication.security_2.entity.AppUser;
import pl.skowrondariusz.TransportApplication.security_2.service.UserDetailsServiceImpl;
import pl.skowrondariusz.TransportApplication.security_2.utils.EncrytedPasswordUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {
    private Logger logger;
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetController.class);

    @Autowired
    private AppUserDAO appUserDAO;
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
        AppUser user = token.getUser();
        String updatedPassword = EncrytedPasswordUtils.encrytePassword(form.getPassword());
        appUserDAO.changePassword(updatedPassword, user);
//        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }

}
