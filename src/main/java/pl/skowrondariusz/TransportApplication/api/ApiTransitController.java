package pl.skowrondariusz.TransportApplication.api;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class ApiTransitController {
    private TransitService transitService;
    private ReportsService reportsService;
    private Logger logger;



    @Autowired
    public void setTransitService(TransitService transitService){
        this.transitService = transitService;
    }

    @Autowired
    public void setReportsService(ReportsService reportsService){
        this.reportsService = reportsService;
    }

    @GetMapping(value = "/api/transits/{id}", produces = "application/json")
    public Optional<Transit> getTransitFromId(@PathVariable Long id){
        return transitService.getTransit(id);
    }


    @GetMapping("/api/transits")
    public Collection<Transit> getAllTransits(){
        return transitService.findAll();
    }

    @PostMapping("/api/transit")
    public Transit createTransit(@RequestBody Transit transit){
        transitService.calculateDistance(transit);
        transitService.addTransit(transit);
        return transit;
    }


//    @GetMapping("/api/reports/daily/{start_date}&{end_date}")
//    public Optional<Reports> getReportFromStartAndEndDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
//        List<Transit> transits = transitService.getTransits(startDate, endDate);
//
//    }

    @RequestMapping(path = "/reports/daily", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
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

    @RequestMapping(path = "/api/reports/daily", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public String getDailyReport1(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate  , @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Reports reports = new Reports();
        reports.setStartDate(startDate);
        reports.setEndDate(endDate);
        reportsService.calculateTotalDistance(reports);
        reportsService.calculateTotalPrice(reports);
        String answer = "Total distance";
        return answer;
    }

    @GetMapping(value = "/api/reports/{id}", produces = "application/json")
    public Optional<Reports> getReportsFromId(@PathVariable Long id){
        return reportsService.getReport(id);
    }

    @GetMapping("/api/reports")
    public Collection<Reports> getAllReports(){
        return reportsService.findAllReports();
    }



}


