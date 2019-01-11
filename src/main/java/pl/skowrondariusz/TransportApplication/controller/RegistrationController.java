package pl.skowrondariusz.TransportApplication.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
//import pl.skowrondariusz.TransportApplication.security_old.GenericResponse;
//import pl.skowrondariusz.TransportApplication.security_old.OnRegistrationCompleteEvent;
//import pl.skowrondariusz.TransportApplication.dto.UserDto;
//import pl.skowrondariusz.TransportApplication.model.User;
//import pl.skowrondariusz.TransportApplication.model.VerificationToken;
//import pl.skowrondariusz.TransportApplication.service.IUserService;
//import pl.skowrondariusz.TransportApplication.validation.EmailExistsException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

//@Controller
//public class RegistrationController {
//    @Autowired
//    private IUserService userService;
//
//    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
//        LOGGER.debug("Registering user account with information: {}", accountDto);
//
//        final User registered;
//        try {
//            registered = userService.registerNewUserAccount(accountDto);
//        } catch (EmailExistsException e) {
//            e.printStackTrace();
//        }
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
//        return new GenericResponse("success");
//    }
//
//    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
//    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
//        Locale locale = request.getLocale();
//        final String result = userService.validateVerificationToken(token);
//        if (result.equals("valid")) {
//            final User user = userService.getUser(token);
//            // if (user.isUsing2FA()) {
//            // model.addAttribute("qr", userService.generateQRUrl(user));
//            // return "redirect:/qrcode.html?lang=" + locale.getLanguage();
//            // }
//            authWithoutPassword(user);
//            model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
//            return "redirect:/console.html?lang=" + locale.getLanguage();
//        }
//
//        model.addAttribute("message", messages.getMessage("auth.message." + result, null, locale));
//        model.addAttribute("expired", "expired".equals(result));
//        model.addAttribute("token", token);
//        return "redirect:/badUser.html?lang=" + locale.getLanguage();
//    }

    // user activation - verification

//    @RequestMapping(value = "/user/resendRegistrationToken", method = RequestMethod.GET)
//    @ResponseBody
//    public GenericResponse resendRegistrationToken(final HttpServletRequest request, @RequestParam("token") final String existingToken) {
//        final VerificationToken newToken = userService.generateNewVerificationToken(existingToken);
//        final User user = userService.getUser(newToken.getToken());
//        mailSender.send(constructResendVerificationTokenEmail(getAppUrl(request), request.getLocale(), newToken, user));
//        return new GenericResponse(messages.getMessage("message.resendToken", null, request.getLocale()));
//    }
//    }

