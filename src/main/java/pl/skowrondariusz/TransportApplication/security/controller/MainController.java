package pl.skowrondariusz.TransportApplication.security;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skowrondariusz.TransportApplication.security.form.LoginForm;
import pl.skowrondariusz.TransportApplication.security.utils.WebUtils;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    private Logger logger;
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    @GetMapping("/")
    public String root() {
        return "index";
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String login(Model model,  @ModelAttribute("loginForm") @Valid LoginForm loginForm) {
//        return "login";
//    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
        String userName = principal.getName();
        System.out.println("User Name: " + userName);
        UserDetails loginedUser = (UserDetails) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        return "userInfoPage";
    }
}
