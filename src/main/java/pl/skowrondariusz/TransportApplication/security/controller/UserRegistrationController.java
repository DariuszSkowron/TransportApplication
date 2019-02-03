package pl.skowrondariusz.TransportApplication.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.skowrondariusz.TransportApplication.security.AppUserValidator;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.Mail;
import pl.skowrondariusz.TransportApplication.security.model.Role;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.model.VerificationToken;
import pl.skowrondariusz.TransportApplication.security.repository.VerificationTokenRepository;
import pl.skowrondariusz.TransportApplication.security.service.EmailService;
import pl.skowrondariusz.TransportApplication.security.service.UserService;
import pl.skowrondariusz.TransportApplication.security.utils.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Controller
@Transactional
@RequestMapping("/registration")
public class UserRegistrationController {
    private Logger logger;
    private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository connectionRepository;

    @Autowired private VerificationTokenRepository verificationTokenRepository;
    @Autowired private EmailService emailService;


    @Autowired
    private AppUserValidator appUserValidator;

        @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {

        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == UserRegistrationForm.class) {
            dataBinder.setValidator(appUserValidator);
        }
    }

    @ModelAttribute("user")
    public UserRegistrationForm userRegistrationForm() {
        return new UserRegistrationForm();
    }

    @GetMapping
    public String showRegistrationForm(WebRequest request, Model model) {
        ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator,
                connectionRepository);
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        UserRegistrationForm user = null;
        if (connection != null) {
            user = new UserRegistrationForm(connection);
        } else {
            user = new UserRegistrationForm();
        }
        model.addAttribute("user", user);

        return "registration";
    }
//


    @PostMapping
    public String registerUserAccount(WebRequest request, Model model, @ModelAttribute("user") @Valid UserRegistrationForm userDto,
                                      BindingResult result, HttpServletRequest httpRequest){


//        User existing = userService.findByEmail(userDto.getEmail());
//        if (existing != null){
//            result.rejectValue("email", null, "There is already an account registered with that email");
//        }

        if (result.hasErrors()){
            return "registration";
        }

        List<String> roleNames = new ArrayList<String>();
        roleNames.add(Role.ROLE_USER);

        User registered = null;

        try{
            registered = userService.registerNewUserAccount(userDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", "Error " + ex.getMessage());
            return "registration";
        }

        if (userDto.getSignInProvider() != null) {
            ProviderSignInUtils providerSignInUtils //
                    = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
            providerSignInUtils.doPostSignUp(registered.getUserName(), request);
        }

//        User registred = userService.save(userDto);

        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(registered);
        token.setExpiryDate(30);
        verificationTokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@skowrondariusz.com");
        mail.setTo(registered.getEmail());
        mail.setSubject("Password reset request");

        Map<String, Object> modelEmail = new HashMap<>();
        modelEmail.put("token", token);
        modelEmail.put("user", registered);
        modelEmail.put("signature", "https://skowrondariusz.com");
        String url = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort();
        modelEmail.put("resetUrl", url + "/registrationConfirm?token=" + token.getToken());
        mail.setModel(modelEmail);
        emailService.sendEmail(mail);

        SecurityUtil.logInUser(registered, roleNames);

        return "redirect:/userInfo";
    }



}

