package pl.skowrondariusz.TransportApplication.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.repository.ReportsRepository;
import pl.skowrondariusz.TransportApplication.service.ReportsService;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class SerializersTest {



    @Test
    public void SerializingDateWithCustomSerializer()
        throws JsonProcessingException{



        ObjectMapper mapper = new ObjectMapper();

        LocalDate date = LocalDate.of(2018,12,20);
        LocalDate date1 = LocalDate.of(2018,12,1);
        LocalDate date2 = LocalDate.of(2018,12,2);
        LocalDate date3 = LocalDate.of(2018,12,3);

        List<MonthlyReport> monthlyReportList = new ArrayList<MonthlyReport>();
        monthlyReportList.add(new MonthlyReport(date));
        monthlyReportList.add(new MonthlyReport(date1));
        monthlyReportList.add(new MonthlyReport(date2));
        monthlyReportList.add(new MonthlyReport(date3));


//        MonthlyReport monthlyReport = new MonthlyReport(date);
//        MonthlyReport monthlyReport1 = new MonthlyReport(date1);
//        MonthlyReport monthlyReport2 = new MonthlyReport(date2);
//        MonthlyReport monthlyReport3 = new MonthlyReport(date3);

        String result = mapper.writeValueAsString(monthlyReportList.get(0));
        String result1 = mapper.writeValueAsString(monthlyReportList.get(1));
        String result2 = mapper.writeValueAsString(monthlyReportList.get(2));
        String result3 = mapper.writeValueAsString(monthlyReportList.get(3));

        assertThat(result, containsString("December, 20th"));
        assertThat(result1, containsString("December, 1st"));
        assertThat(result2, containsString("December, 2nd"));
        assertThat(result3, containsString("December, 3rd"));


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