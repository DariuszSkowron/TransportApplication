package pl.skowrondariusz.TransportApplication.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.logging.log4j.util.EnglishEnums;
import org.springframework.format.annotation.DateTimeFormat;
import pl.skowrondariusz.TransportApplication.config.DateSerializer;
import pl.skowrondariusz.TransportApplication.config.DistanceSerializer;
import pl.skowrondariusz.TransportApplication.config.PriceSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class MonthlyReport  {

    //    @JsonFormat(pattern = "MMMM, d", locale = "en_US")
    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using=DateSerializer.class)
//    @JsonFormat(pattern = "MMMM, d", locale = "en_US")
    private LocalDate date;
    @JsonSerialize(using=DistanceSerializer.class)
    private Long totalDistance;
    @JsonSerialize(using=DistanceSerializer.class)
    private Long averageDistance;
    @JsonSerialize(using=PriceSerializer.class)
    private Long averagePrice;


//
//    public MonthlyReport(LocalDate startDate, LocalDate endDate) {
//        super(startDate, endDate);
//    }

    public MonthlyReport() {
    }

    public MonthlyReport(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
//    @JsonSerialize(using=DateSerializer.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Long getAverageDistance() {
        return averageDistance;
    }

    @JsonProperty("avg_distance")
    public void setAverageDistance(Long averageDistance) {
        this.averageDistance = averageDistance;
    }

    @JsonProperty("avg_price")
    public Long getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }

    @JsonProperty("total_distance")
    public Long getTotalDistance() {
        return totalDistance;
    }


    public void setTotalDistance(Long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
