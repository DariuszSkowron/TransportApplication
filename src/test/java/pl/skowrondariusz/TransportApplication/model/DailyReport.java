package pl.skowrondariusz.TransportApplication.model;

import java.util.Date;

public class DailyReport {

    private Date startDate;
    private Date endDate;
    private Long totalDistance;
    private Long totalPrice;

    public DailyReport() {
    }

    public DailyReport(Date startDate, Date endDate, Long totalDistance, Long totalPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDistance = totalDistance;
        this.totalPrice = totalPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "DailyReport{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalDistance=" + totalDistance +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
