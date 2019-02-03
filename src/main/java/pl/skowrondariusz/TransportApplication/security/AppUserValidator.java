package pl.skowrondariusz.TransportApplication.security;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.skowrondariusz.TransportApplication.security.constraint.PasswordConstraintValidator;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;
import pl.skowrondariusz.TransportApplication.security.model.User;
import pl.skowrondariusz.TransportApplication.security.repository.UserRepository;
import pl.skowrondariusz.TransportApplication.security.service.UserService;
import pl.skowrondariusz.TransportApplication.security.service.UserServiceImpl;



@Component
    public class AppUserValidator implements Validator {

        private EmailValidator emailValidator = EmailValidator.getInstance();



        @Autowired
        private UserService userService;


        @Override
        public boolean supports(Class<?> clazz) {
            return clazz == UserRegistrationForm.class;
        }

        @Override
        public void validate(Object target, Errors errors) {

            UserRegistrationForm form = (UserRegistrationForm) target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "terms", "", "You have to accept terms and conditions to register account");

            if (errors.hasErrors()) {
                return;
            }

            if (!emailValidator.isValid(form.getEmail())) {

                errors.rejectValue("email", "", "Email is not valid");
                return;
            }

            if (!Boolean.TRUE.equals(form.getTerms())){
                errors.rejectValue("terms", "", "You have to accept terms and conditions to register account");
            }

            User userAccount = userService.findByUserName(form.getUserName());
            if (userAccount != null) {
                if (form.getUserId() == null) {
                    errors.rejectValue("userName", "", "User name is not available");
                    return;
                } else if (!form.getUserId().equals(userAccount.getUserId())) {
                    errors.rejectValue("userName", "", "User name is not available");
                    return;
                }
            }

            userAccount = userService.findByEmail(form.getEmail());
            if (userAccount != null) {
                if (form.getUserId() == null) {
                    errors.rejectValue("email", "", "Email is not available");
                    return;
                } else if (!form.getUserId().equals(userAccount.getUserId())) {
                    errors.rejectValue("email", "", "Email is not available");
                    return;
                }
            }


        }

    }
