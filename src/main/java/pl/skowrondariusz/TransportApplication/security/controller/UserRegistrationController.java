package pl.skowrondariusz.TransportApplication.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationEventPublisher;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.context.request.WebRequest;

import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.OnRegistrationCompleteEvent;
import pl.skowrondariusz.TransportApplication.security.model.Role;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.service.UserService;
import pl.skowrondariusz.TransportApplication.security.utils.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/registration")
public class UserRegistrationController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    ApplicationEventPublisher eventPublisher;
    private Logger logger;
    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository connectionRepository;

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

    @PostMapping
    public String registerUserAccount(WebRequest request, Model model, @ModelAttribute("user") @Valid UserRegistrationForm userDto,
                                      BindingResult result, HttpServletRequest httpRequest) {

        User existingEmailUser = userService.findByEmail(userDto.getEmail());
        if (existingEmailUser != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        User existingUserNameUser = userService.findByUserName(userDto.getUserName());
        if (existingUserNameUser != null) {
            result.rejectValue("userName", null, "The username is already taken");
        }

        if (result.hasErrors()) {
            return "registration";
        }


        List<String> roleNames = new ArrayList<String>();
        roleNames.add(Role.ROLE_USER);

        User registered = null;

        try {
            registered = userService.registerNewUserAccount(userDto);
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl, httpRequest));
        } catch (Exception ex) {
            ex.printStackTrace();
            model.addAttribute("errorMessage", "Error " + ex.getMessage());
            return "registration";
        }

        if (userDto.getSignInProvider() != null) {
            ProviderSignInUtils providerSignInUtils
                    = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
            providerSignInUtils.doPostSignUp(registered.getUserName(), request);
        }

        SecurityUtil.logInUser(registered, roleNames);

        return "redirect:/registration?success";
    }

}

