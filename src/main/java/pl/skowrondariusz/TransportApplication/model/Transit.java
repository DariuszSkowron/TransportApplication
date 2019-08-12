package pl.skowrondariusz.TransportApplication.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import pl.skowrondariusz.TransportApplication.config.DateSerializerNumberTwo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Transit {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String sourceAdress;
    @NotNull
    private String destinationAdress;
    private Double price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using = DateSerializerNumberTwo.class)
    private LocalDate date;
    private Long distance;

    public Transit() {
    }

    public Transit(String sourceAdress, String destinationAdress, Double price, LocalDate date) {
        this.sourceAdress = sourceAdress;
        this.destinationAdress = destinationAdress;
        this.price = price;
        this.date = date;
    }

    public Transit(String sourceAdress, String destinationAdress, double price, LocalDate date, Long distance) {
        this.sourceAdress = sourceAdress;
        this.destinationAdress = destinationAdress;
        this.price = price;
        this.date = date;
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSourceAdress() {
        return sourceAdress;
    }

    public void setSourceAdress(String sourceAdress) {
        this.sourceAdress = sourceAdress;
    }

    public String getDestinationAdress() {
        return destinationAdress;
    }

    public void setDestinationAdress(String destinationAdress) {
        this.destinationAdress = destinationAdress;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transit transit = (Transit) o;
        return Objects.equals(id, transit.id) &&
                Objects.equals(sourceAdress, transit.sourceAdress) &&
                Objects.equals(destinationAdress, transit.destinationAdress) &&
                Objects.equals(distance, transit.distance) &&
                Objects.equals(date, transit.date) &&
                Objects.equals(price, transit.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, sourceAdress, destinationAdress, distance, date, price);
    }

    @Override
    public String toString() {
        return "Transit{" +
                "id=" + id +
                ", sourceAdress='" + sourceAdress + '\'' +
                ", destinationAdress='" + destinationAdress + '\'' +
                ", distance=" + distance + "km" +
                ", date=" + date +
                ", price=" + price + "PLN" +
                '}';
    }
}
