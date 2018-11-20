package pl.skowrondariusz.TransportApplication.model;


import java.util.Date;

public class Transit {



    private Long id;
    private String sourceAdress;
    private String destinationAdress;
    private Long price;
    private Date date;
    private Long distance;

    public Transit() {
    }

    public Transit(String sourceAdress, String destinationAdress, Long price, Date date) {
        this.sourceAdress = sourceAdress;
        this.destinationAdress = destinationAdress;
        this.price = price;
        this.date = date;
    }

    public Transit(Long id, String sourceAdress, String destinationAdress, Long price, Date date, Long distance) {
        this.id = id;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transit{" +
                "id=" + id +
                ", sourceAdress='" + sourceAdress + '\'' +
                ", destinationAdress='" + destinationAdress + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", distance=" + distance +
                '}';
    }
}
