package pl.skowrondariusz.TransportApplication.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skowrondariusz.TransportApplication.security.form.LoginForm;

import javax.validation.Valid;

@Controller
@RequestMapping ("/login")
public class LoginController {



    @ModelAttribute("loginForm")
    public LoginForm loginForm(){
        return new LoginForm();
    }

    @GetMapping
    public String login(Model model) {
        return "login";
    }

    @PostMapping
    public String login(Model model,  @ModelAttribute("loginForm") @Valid LoginForm loginForm) {


        return "login";
    }
}
