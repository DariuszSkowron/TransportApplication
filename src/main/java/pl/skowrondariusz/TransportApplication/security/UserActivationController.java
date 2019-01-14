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

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserActivationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;



    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {

            model.addAttribute("error","Could not find password reset token." );
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        else if (verificationToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");

        }
        final User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login.html";
    }



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

