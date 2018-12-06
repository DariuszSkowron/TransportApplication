package pl.skowrondariusz.TransportApplication.api;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
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
    public Reports getDailyReport1(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate  , @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Reports reports = new Reports();
        reportsService.addReports(reports);
        reports.setStartDate(startDate);
        reports.setEndDate(endDate);
        reports.setTotalDistance(reportsService.calculateTotalDistance(startDate, endDate));
        reports.setTotalPrice(reportsService.calculateTotalPrice(startDate, endDate));
        return reports;
    }

    @RequestMapping(path = "/api/reports/monthly", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public MonthlyReport getMonthlyReport1() {
        MonthlyReport monthlyReport = new MonthlyReport();
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = LocalDate.now();
        LocalDate.now().getDayOfMonth();
        monthlyReport.setTotalDistance(reportsService.calculateTotalDistance(startDate, endDate));
        monthlyReport.setTotalPrice(reportsService.calculateTotalPrice(startDate, endDate));

        return monthlyReport;
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


