package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;
import pl.skowrondariusz.TransportApplication.model.Reports;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.*;

public class SerializersTest {



    @Test
    public void SerializingDateWithCustomSerializer()
        throws JsonProcessingException{

        LocalDate date = LocalDate.of(2018,12,20);
        MonthlyReport monthlyReport = new MonthlyReport(date);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(monthlyReport);
        assertThat(result, containsString("December, 20th"));
    }


    @Test
    public void SerializePrice()
        throws JsonProcessingException{

        Long testingPrice = 120l;
        Reports reports = new Reports();
        reports.setTotalPrice(testingPrice);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(reports);
        assertThat(result, containsString("120 PLN"));

    }

    @Test
    public void SerializeDistance()
        throws JsonProcessingException{

        Long testingDistance = 244l;
        Reports reports = new Reports();
        reports.setTotalDistance(testingDistance);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(reports);
        assertThat(result, containsString("244 km"));
    }

}