package pl.skowrondariusz.TransportApplication.api;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.List;


@RestController
public class ApiTransitController {
    private TransitService transitService;
    private Logger logger;


    @Autowired
    public void setTransitService(TransitService transitService){
        this.transitService = transitService;
    }


    @RequestMapping(path = "/reports/daily", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getDailyReport(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate  , @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        double totalDistance = 0.0;
        double totalPrice = 0.0;
        List<Transit> transits = transitService.getTransits(startDate, endDate);
        for (Transit transit : transits) {
            if (transit.getDistance() != null && transit.getPrice() != null) {
                try {
                    totalDistance = totalDistance + transit.getDistance();
                    totalPrice = totalPrice + transit.getPrice();

                } catch (NullPointerException e) {
                    logger.error("Nullpointer exception", e);
                }
            }
        }
        return "Total distance" + totalDistance + ", total price: " + totalPrice;
    }

    @RequestMapping(path = "reports/monthly", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getMonthlyReport() {
        double totalDistance = 0.0;
        double totalPrice = 0.0;
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate currentDate = LocalDate.now();
        List<Transit> transits = transitService.getTransits(startDate, currentDate);
        for (Transit transit : transits) {
            if (transit.getDistance() != null && transit.getPrice() != null) {
                try {
                    totalDistance = totalDistance + transit.getDistance();
                    totalPrice = totalPrice + transit.getPrice();
                } catch (NullPointerException e) {
                    logger.error("Nullpointer exception", e);
                }
            }
        }
        return "Total distance " + totalDistance + ", total price: " + totalPrice;

    }
}


