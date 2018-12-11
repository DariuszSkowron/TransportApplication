package pl.skowrondariusz.TransportApplication.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,WebMvcAutoConfiguration.class })
@RunWith(SpringRunner.class)
@WebMvcTest(ApiTransitController.class)
public class ApiTransitControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransitService transitService;

    @MockBean
    private ReportsService reportsService;

    @Test
    public void shouldAddTransitAndCalculateDistance() throws Exception {


        Transit transit = new Transit("Poznań", "Kraków", 12L, LocalDate.of(2018, 12, 5));

        given(transitService.findAll()).willReturn((List<Transit>) transit);

        this.mockMvc.perform(get("api/transits"))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0],sourceAdress", is("Poznań")));
    }


//    @Test
//    public void shouldCreateBook() throws Exception {
//
//        Transit transit = new Transit("Poznań", "Kraków", 12L, LocalDate.of(2018, 12, 5));
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//        this.mockMvc.perform(post("/api/book")
//                .andExpect(jsonPath(""));
//
//        verify(transitService).addTransit(eq(new Transit("Poznań", "Kraków", 12L, LocalDate.of(2018, 12, 5))));
//    }
}