package pl.skowrondariusz.TransportApplication.security.controller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.skowrondariusz.TransportApplication.security.recaptcha.ReCaptchaService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
//@ContextConfiguration(classes= SpringTestConfig.class, loader= AnnotationConfigContextLoader.class)
public class RecaptchaValidationTest {

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Test
    public void submitWithoutReCaptcha() throws Exception {
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "hiremeplease@gmail.com")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitWithInvalidReCaptcha() throws Exception {
        String invalidReCaptcha = "invalid-re-captcha";
        when(reCaptchaService.validate(invalidReCaptcha)).thenReturn(false);
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "hiremeplease@gmail.com")
                                .param("reCaptchaResponse", invalidReCaptcha)
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitWithValidReCaptcha() throws Exception {
        String validReCaptcha = "valid-re-captcha";
        when(reCaptchaService.validate(validReCaptcha)).thenReturn(true);
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "hiremeplease@gmail.com")
                                .param("reCaptchaResponse", validReCaptcha)
                )
                .andExpect(model().hasNoErrors())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forgot-password?success"));
    }

}
