package pl.skowrondariusz.TransportApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.ReportsRepository;


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
                totalPrice = totalPrice + transit.getPrice();
            }
        }
        reports.setTotalPrice((long) totalPrice);
    }

    public String addReports1(LocalDate startDate, LocalDate endDate) {
        Reports reports = new Reports();
        reports.setStartDate(startDate);
        reports.setEndDate(endDate);
        calculateTotalDistance(reports);
        calculateTotalPrice(reports);
        reportsRepository.save(reports);
        return "Total distance " + reports.getTotalDistance()+ ", total price: " + reports.getTotalPrice();
    }
}