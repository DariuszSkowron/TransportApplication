package pl.skowrondariusz.TransportApplication.security.constraint;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.skowrondariusz.TransportApplication.security.form.UserRegistrationForm;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import java.util.Set;

import static org.junit.Assert.*;

public class PasswordConstraintValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testInvalidPassword() {
        UserRegistrationForm userRegistration = new UserRegistrationForm();
        userRegistration.setFirstName("Dariusz");
        userRegistration.setLastName("S");
        userRegistration.setUserName("skowron");
        userRegistration.setEmail("test@gmail.com");
        userRegistration.setConfirmEmail("test@gmail.com");
        userRegistration.setPassword("password");
        userRegistration.setConfirmPassword("password");
        userRegistration.setTerms(true);

        Set<ConstraintViolation<UserRegistrationForm>> constraintViolations = validator.validate(userRegistration);

        Assert.assertEquals(constraintViolations.size(), 2);
    }

    @Test
    public void testValidPasswords() {
        UserRegistrationForm userRegistration = new UserRegistrationForm();
        userRegistration.setFirstName("Dariusz");
        userRegistration.setLastName("S");
        userRegistration.setUserName("skowron");
        userRegistration.setEmail("test@gmail.com");
        userRegistration.setConfirmEmail("test@gmail.com");
        userRegistration.setPassword("xJ3!dij50");
        userRegistration.setConfirmPassword("xJ3!dij50");
        userRegistration.setTerms(true);

        Set<ConstraintViolation<UserRegistrationForm>> constraintViolations = validator.validate(userRegistration);

        Assert.assertEquals(constraintViolations.size(), 0);
    }
}