//package pl.skowrondariusz.TransportApplication.api;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import pl.skowrondariusz.TransportApplication.model.Reports;
//import pl.skowrondariusz.TransportApplication.repository.ReportsRepository;
//import pl.skowrondariusz.TransportApplication.service.ReportsService;
//import pl.skowrondariusz.TransportApplication.service.TransitService;
//
//import java.time.LocalDate;
//
//@RestController
//public class ApiReportsController {
//    private ReportsService reportsService;
//
//
//    @Autowired
//    public void setReportsService(ReportsService reportsService){
//        this.reportsService = reportsService;
//    }
//
//
//
//    @RequestMapping(path = "/reports/daily", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
//    public String getDailyReport(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate  , @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        Reports reports = new Reports();
//        reportsService.addReports(reports);
//        reportsService.calculateTotalDistance(reports);
//        reportsService.calculateTotalPrice(reports);
//        reportsService.addReports(reports);
//        return "Total distance" + reports.getTotalDistance() + ", total price: " + reports.getTotalPrice();
//    }
//
//}


