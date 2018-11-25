package pl.skowrondariusz.TransportApplication.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import java.util.Date;
import java.util.List;

@RestController
public class ApiTransitController {

    @Autowired
    private TransitService transitService;


    @GetMapping("/reports/daily")
    public void getDailyReport(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate){
        double totalDistance = 0.0;
        double totalPrice = 0.0;
        List<Transit> transits = transitService.getTransits(startDate, endDate);
        for (int i = 0; i < transits.size(); i++) {
            totalDistance += transits.get(i).getDistance();
            totalPrice += transits.get(i).getPrice();
        }
    }
}
