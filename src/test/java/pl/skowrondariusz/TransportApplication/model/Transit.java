package pl.skowrondariusz.TransportApplication.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Transit {



    private Long id;
    private String sourceAdress;
    private String destinationAdress;
    private Long price;
    private Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = dateFormat.format(date);
    private Long distance;

    public Transit(String poznan, String warszawa, long l, String s) {
    }


    public Transit(Long id, String sourceAdress, String destinationAdress, Long price, String dateString) {
        this.id = id;
        this.sourceAdress = sourceAdress;
        this.destinationAdress = destinationAdress;
        this.price = price;
        this.dateString = dateString;
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
