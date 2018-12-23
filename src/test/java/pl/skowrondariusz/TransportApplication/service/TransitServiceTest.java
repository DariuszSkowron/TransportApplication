//package pl.skowrondariusz.TransportApplication.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import pl.skowrondariusz.TransportApplication.model.Transit;
//import static org.junit.Assert.*;
//import static org.hamcrest.Matchers.containsString;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TransitServiceTest {
//
//
//    @MockBean
//    TransitService transitService;
//
//    @Test
//    public void DistanceCalculatingTest() throws Exception{
//
//
//
//
//        ObjectMapper mapper = new ObjectMapper();
//
////        List<Transit> transitList = new ArrayList<>();
//        Transit transit = new Transit("Poznań", "Kraków", 12L, LocalDate.of(2018, 12, 5));
//
//        String result = mapper.writeValueAsString(transitService.getTransit(0L).toString());
//        assertThat(result, containsString("distance: " + "null"));
//    }
//
//}