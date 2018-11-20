package pl.skowrondariusz.TransportApplication.model;

import java.util.Date;

public class MonthlyReport {

    private Date date;
    private Long totalDistance;
    private Long averageDistance;
    private Long averagePrice;

    public MonthlyReport() {
    }

    public MonthlyReport(Date date, Long totalDistance, Long averageDistance, Long averagePrice) {
        this.date = date;
        this.totalDistance = totalDistance;
        this.averageDistance = averageDistance;
        this.averagePrice = averagePrice;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
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
    public String toString() {
        return "MonthlyReport{" +
                "date=" + date +
                ", totalDistance=" + totalDistance +
                ", averageDistance=" + averageDistance +
                ", averagePrice=" + averagePrice +
                '}';
    }
}
