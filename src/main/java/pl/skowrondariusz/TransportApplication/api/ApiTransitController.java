package pl.skowrondariusz.TransportApplication.api;


import com.fasterxml.jackson.databind.util.JSONPObject;
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
import java.util.Optional;


@RestController
public class ApiTransitController {
    private TransitService transitService;
    private Logger logger;


    @Autowired
    public void setTransitService(TransitService transitService){
        this.transitService = transitService;
    }

    @GetMapping("/transits/{id}")
    public Optional<Transit> getTransitFromId(@PathVariable Long id){
        return transitService.getTransit(id);
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
//        JSONPObject myResponse = new JSONPObject("totalDistance");
    }

    @GetMapping("reports/monthly")
    public String getMonthlyReport() {

        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate currentDate = LocalDate.now();
        List<Transit> transits = transitService.getTransits(startDate, currentDate);
        long totalDistance = 0;
        long totalPrice = 0;
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


