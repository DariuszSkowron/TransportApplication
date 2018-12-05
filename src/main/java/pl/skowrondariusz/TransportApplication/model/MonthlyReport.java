package pl.skowrondariusz.TransportApplication.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MonthlyReport extends Reports {


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private Long average;


    public MonthlyReport(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    public MonthlyReport() {
    }


}
