package pl.skowrondariusz.TransportApplication.security.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordResetTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tryPasswordResetWithoutToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void tryPasswordResetWithInvalidToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password?token=invalid-token")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void tryPasswordResetWithExpiredToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password?token=expired-token")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitPasswordResetSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/reset-password")
                                .with(csrf())
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("token", "valid-token")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/login?resetSuccess"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submitPasswordResetPasswordDoNotMatch() throws Exception {
        this.mockMvc
                .perform(
                        post("/reset-password")
                                .with(csrf())
                                .param("password", "password")
                                .param("confirmPassword", "invalid-password")
                                .param("token", "valid-token")
                )
                .andExpect(flash().attributeExists(BindingResult.class.getName() + ".passwordResetForm"))
                .andExpect(redirectedUrl("/reset-password?token=valid-token"))
                .andExpect(status().is3xxRedirection());
    }
}