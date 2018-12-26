package pl.skowrondariusz.TransportApplication.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TestService;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
    private TestService testService;

    @MockBean
    private TransitRepository transitRepository;

    @MockBean
    private ReportsService reportsService;

    @MockBean
    private TransitService transitService;



    @Test
    public void shouldCreateTest() throws Exception{

        pl.skowrondariusz.TransportApplication.model.Test test = new pl.skowrondariusz.TransportApplication.model.Test("Test");

        ObjectMapper objectMapper = new ObjectMapper();
        String testJsonString = objectMapper.writeValueAsString(test);

        this.mockMvc.perform(post("/api/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testJsonString))
                .andExpect(status().isCreated());

        verify(testService).addTest(eq(new pl.skowrondariusz.TransportApplication.model.Test("Test")));
    }

//    @MockBean
//    private TransitService transitService;
//
//    @MockBean
//    private ReportsService reportsService;

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


    @Test
    public void shouldCreateBook() throws Exception {

//        DateTimeFormatter formatter =
//                DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        cal.set(2012, 11, 20);
//        LocalDate date3 = Calendar.getTime();


        Transit transit = new Transit("Poznań", "Kraków", 12d,LocalDate.of(2018, 12, 5));

        ObjectMapper objectMapper = new ObjectMapper();
        String transitJsonString = objectMapper.writeValueAsString(transit);


        this.mockMvc.perform(post("/api/transit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transitJsonString))
                .andExpect(status().isCreated());

        verify(transitService).addTransit(eq(new Transit("Poznań", "Kraków", 12d, LocalDate.of(2018,12,5))));
    }


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
