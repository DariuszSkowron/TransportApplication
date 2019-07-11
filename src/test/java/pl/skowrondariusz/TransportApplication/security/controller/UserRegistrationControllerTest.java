package pl.skowrondariusz.TransportApplication.security.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.skowrondariusz.TransportApplication.security.recaptcha.ReCaptchaService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@ContextConfiguration(locations = {"classpath:application.yml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Test
    public void submitRegistrationPasswordNotValid() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .param("firstName", "Dariusz")
                                .param("lastName", "Scovron")
                                .param("userName", "Scovron")
                                .param("email", "test@gmail.com")
                                .param("confirmEmail", "test@gmail.com")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "password", "confirmPassword"))
                .andExpect(status().isOk());

    }

    @Test
    public void submitRegistrationPasswordNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .param("firstName", "Dariusz")
                                .param("lastName", "Scovron")
                                .param("userName", "Scovron")
                                .param("email", "test@gmail.com")
                                .param("confirmEmail", "test@gmail.com")
                                .param("password", "Testing132!")
                                .param("confirmPassword", "Testing131!")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "dsfgdsgdg")
                                .param("lastName", "sdfgdsfgsd")
                                .param("userName", "lolololo")
                                .param("email", "test@gmail.com")
                                .param("confirmEmail", "test@gmail.com")
                                .param("password", "Testing132!")
                                .param("confirmPassword", "Testing132!")
                                .param("terms", "on")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/registration?success"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submitWithoutReCaptcha() throws Exception {
        this.mockMvc
                .perform(
                        post("/forgot-password")
                                .param("email", "new@memorynotfound.com")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("forgotPasswordForm", "reCaptchaResponse"))
                .andExpect(status().isOk());
    }

}