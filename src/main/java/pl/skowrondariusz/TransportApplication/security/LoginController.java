//package pl.skowrondariusz.TransportApplication.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    @Autowired
//    private UserService userService;
//
//
//    @ModelAttribute("loginForm")
//    public LoginDto loginDto() {
//        return new LoginDto();
//    }
//
//    @GetMapping
//    public String displayLoginPage() {
//        return "login";
//    }
//
//    @PostMapping
//    public String processLoginForm(@ModelAttribute("loginForm") @Valid LoginDto form,
//                                            BindingResult result,
//                                            HttpServletRequest request) {
//
//        if (result.hasErrors()) {
//            return "login";
//        }
//
//        User user = userService.findByEmail(form.getEmail());
//        if (user == null) {
//            result.rejectValue("global.error", null, "We could not find an account for that e-mail address.");
//            return "login";
//        } else if (!user.isEnabled()) {
//            result.rejectValue("error", null, "Your account is not activated, click here to resend activation token.");
//            return "login";
//        }
//
//
//        return "redirect:/login?success";
//    }
//
//
//
//
//}
