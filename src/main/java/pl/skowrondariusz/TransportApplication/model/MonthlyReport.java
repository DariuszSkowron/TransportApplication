package pl.skowrondariusz.TransportApplication.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MonthlyReport extends Reports {


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private Long averageDistance;
    private Long averagePrice;
    private Long totalDistance;


    public MonthlyReport(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    public MonthlyReport() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(Long averageDistance) {
        this.averageDistance = averageDistance;
    }

    public Long getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public Long getTotalDistance() {
        return totalDistance;
    }

    @Override
    public void setTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
    }
}
