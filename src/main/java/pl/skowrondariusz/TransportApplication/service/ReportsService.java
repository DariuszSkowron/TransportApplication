package pl.skowrondariusz.TransportApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.ReportsRepository;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ReportsService {

    @Autowired
    TransitRepository transitRepository;

    @Autowired
    ReportsRepository reportsRepository;

    public void addReport(Reports reports){
        List<Transit> transits = transitService.getService(startDate, endDate);
        for(int i = 0; i < transits.size(); i++) {
            System.out.println(transits.get(i).toString());
            totalPrice = totalPrice + transit.getPrice();
            totalDistance = totalDistance + transit.getDistance();
        }
        return reportsRepository.save(report)
    }

//    double totalDistance = 0.0;
//    double totalPrice = 0.0;
//    List<Transit> transits = transitService.getTransits(startDate, endDate);
//        for (Transit transit : transits) {
//        if (transit.getDistance() != null && transit.getPrice() != null) {
//            try {
//                totalDistance = totalDistance + transit.getDistance();
//                totalPrice = totalPrice + transit.getPrice();
//
//            } catch (NullPointerException e) {
//                logger.error("Nullpointer exception", e);
//            }
//        }
//    }
//        return "Total distance" + totalDistance + ", total price: " + totalPrice;
//        JSONPObject myResponse = new JSONPObject("totalDistance");
}
}


//public List<Transit> getTransits(LocalDate startDate, LocalDate endDate) {
//        List<Transit> transits = transitRepository.find(startDate, endDate);
//        for(int i = 0; i < transits.size(); i++) {
//        System.out.println(transits.get(i).toString());
//        }
//        return transits;
//        }