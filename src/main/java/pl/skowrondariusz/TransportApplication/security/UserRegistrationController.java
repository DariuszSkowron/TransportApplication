//package pl.skowrondariusz.TransportApplication.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.*;
//
//@Controller
//@RequestMapping("/registration")
//public class UserRegistrationController {
//    private Logger logger;
//    private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationController.class);
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired private VerificationTokenRepository verificationTokenRepository;
//    @Autowired private EmailService emailService;
//
//
//    @ModelAttribute("user")
//    public UserRegistrationForm userRegistrationDto() {
//        return new UserRegistrationForm();
//    }
//
//    @GetMapping
//    public String showRegistrationForm(Model model) {
//        return "registration";
//    }
//
//
//
//    @PostMapping
//    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationForm userDto,
//                                      BindingResult result, HttpServletRequest request){
//
//
//        User existing = userService.findByEmail(userDto.getEmail());
//        if (existing != null){
//            result.rejectValue("email", null, "There is already an account registered with that email");
//        }
//
//        if (result.hasErrors()){
//            return "registration";
//        }
//
//        User registred = userService.save(userDto);
//
//        VerificationToken token = new VerificationToken();
//        token.setToken(UUID.randomUUID().toString());
//        token.setUser(registred);
//        token.setExpiryDate(30);
//        verificationTokenRepository.save(token);
//
//        Mail mail = new Mail();
//        mail.setFrom("no-reply@skowrondariusz.com");
//        mail.setTo(registred.getEmail());
//        mail.setSubject("Password reset request");
//
//        Map<String, Object> model = new HashMap<>();
//        model.put("token", token);
//        model.put("user", registred);
//        model.put("signature", "https://skowrondariusz.com");
//        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//        model.put("resetUrl", url + "/registrationConfirm?token=" + token.getToken());
//        mail.setModel(model);
//        emailService.sendEmail(mail);
//
//        return "redirect:/registration?success";
//    }
//
//
//
//}
