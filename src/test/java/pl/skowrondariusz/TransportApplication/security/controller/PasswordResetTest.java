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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordResetTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessPasswordResetWithoutToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }
}
