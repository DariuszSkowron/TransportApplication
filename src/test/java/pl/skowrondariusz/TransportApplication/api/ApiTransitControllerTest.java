package pl.skowrondariusz.TransportApplication.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiTransitController.class)
public class ApiTransitControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransitRepository transitRepository;

    @MockBean
    private ReportsService reportsService;

    @MockBean
    private TransitService transitService;
    
    @WithMockUser(value = "spring")
    @Test
    public void shouldAddTransitAndCalculateDistance() throws Exception {

        List<Transit> transitList = Arrays.asList(
                new Transit("Poznań", "Kraków", 12d, LocalDate.of(2018, 12, 5)));

        given(transitService.findAll()).willReturn(transitList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].date", is("2018-12-05")));
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void shouldCreateBook() throws Exception {

        Transit transit = new Transit("Poznań", "Kraków", 12d, LocalDate.of(2018, 12, 5));

        ObjectMapper objectMapper = new ObjectMapper();
        String transitJsonString = objectMapper.writeValueAsString(transit);


        this.mockMvc.perform(post("/api/transit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transitJsonString))
                .andExpect(status().isCreated());

        verify(transitService).addTransit(eq(new Transit("Poznań", "Kraków", 12d, LocalDate.of(2018, 12, 5))));
    }
    
    @WithMockUser(value = "spring")
    @Test
    public void shouldGetTransitFromId() throws Exception {

        List<Transit> transitList = Arrays.asList(
                new Transit("Poznań", "Kraków", 12d, LocalDate.of(2018, 12, 5)),
                new Transit("Warsaw", "Kraków", 12d, LocalDate.of(2018, 12, 5)),
                new Transit("Rzeszow", "Kraków", 12d, LocalDate.of(2018, 12, 5)));

        when(transitService.getTransit(1L)).thenReturn(java.util.Optional.ofNullable(transitList.get(1)));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transits/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sourceAdress", is("Warsaw")));

    }
}
