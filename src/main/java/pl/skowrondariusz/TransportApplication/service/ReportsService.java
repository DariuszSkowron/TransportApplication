package pl.skowrondariusz.TransportApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.ReportsRepository;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;


import java.time.LocalDate;
import java.util.List;

@Service
public class ReportsService {

        @Autowired
    TransitService transitService;
//
////    @Autowired
////    TransitRepository transitRepository;
//
    @Autowired
    ReportsRepository reportsRepository;


    public void addReports(Reports reports) {
        reportsRepository.save(reports);
    }


    public void calculateTotalDistance(Reports reports) {
        double totalDistance = 0.0;
        List<Transit> transits = transitService.getTransits(reports.getStartDate(), reports.getEndDate());
        for (Transit transit : transits) {
            if (transit.getDistance() != null) {
                totalDistance = totalDistance + transit.getDistance();
            }
        }
        reports.setTotalDistance((long) totalDistance);
    }


    public void calculateTotalPrice(Reports reports) {
        double totalPrice = 0.0;
        List<Transit> transits = transitService.getTransits(reports.getStartDate(), reports.getEndDate());
        for (Transit transit : transits) {
            if (transit.getPrice() != null) {
                totalPrice = totalPrice + transit.getDistance();
            }
        }
        reports.setTotalDistance((long) totalPrice);
    }
//
//    }

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
//}
//}


//public List<Transit> getTransits(LocalDate startDate, LocalDate endDate) {
//        List<Transit> transits = transitRepository.find(startDate, endDate);
//        for(int i = 0; i < transits.size(); i++) {
//        System.out.println(transits.get(i).toString());
//        }
//        return transits;
//        }