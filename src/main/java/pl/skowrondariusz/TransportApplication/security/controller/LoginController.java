package pl.skowrondariusz.TransportApplication.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skowrondariusz.TransportApplication.security.form.LoginForm;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping ("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @ModelAttribute("loginForm")
    public LoginForm loginForm(){
        return new LoginForm();
    }

    @GetMapping
    public String login(Model model) {
        return "login";
    }

    @PostMapping
    public String login(Model model,  @ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result) {
        User existing = userService.findByUserName(loginForm.getUserName());
        if (existing == null) result.rejectValue("userName", null, "There is no registered user with this username");

        if (result.hasErrors()){
            return "login";
        }

        return "login";
    }
}
