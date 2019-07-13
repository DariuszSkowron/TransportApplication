package pl.skowrondariusz.TransportApplication.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TransitService;
import pl.skowrondariusz.TransportApplication.view.ExcelReportView;
import pl.skowrondariusz.TransportApplication.view.PdfView;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class ApiTransitController {
    private static final Logger LOG = LoggerFactory.getLogger(ApiTransitController.class);
    private TransitService transitService;
    private ReportsService reportsService;
    private Logger logger;

    @Autowired
    public void setTransitService(TransitService transitService) {
        this.transitService = transitService;
    }

    @Autowired
    public void setReportsService(ReportsService reportsService) {
        this.reportsService = reportsService;
    }


    @GetMapping(value = "/api/transits/{id}", produces = "application/json")
    public Optional<Transit> getTransitFromId(@PathVariable Long id) {
        return transitService.getTransit(id);
    }

    @GetMapping("/api/transits")
    public Collection<Transit> getAllTransits() {
        return transitService.findAll();
    }

    @GetMapping("/api/transits/download")
    public ModelAndView getExcel() {
        return new ModelAndView(new ExcelReportView(), "transitList", transitService.findAll());
    }

    @GetMapping("/api/transits/downloadPDF")
    public ModelAndView getPdf() {
        return new ModelAndView(new PdfView(), "transitList", transitService.findAll());
    }

    @PostMapping("/api/transit")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransit(@RequestBody Transit transit) {
        LOG.info("Saving transit={}", transit);
        transitService.calculateDistance(transit);
        transitService.addTransit(transit);
    }

    @RequestMapping(path = "/api/reports/daily", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public Reports getDailyReport1(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Reports reports = new Reports();
        reportsService.addReports(reports);
        reports.setStartDate(startDate);
        reports.setEndDate(endDate);
        reports.setTotalDistance(reportsService.calculateTotalDistance(startDate, endDate));
        reports.setTotalPrice(reportsService.calculateTotalPrice(startDate, endDate));
        return reports;
    }

    @GetMapping(value = "/api/reports/{id}", produces = "application/json")
    public Optional<Reports> getReportsFromId(@PathVariable Long id) {
        return reportsService.getReport(id);
    }

    @GetMapping("/api/reports")
    public Collection<Reports> getAllReports() {
        return reportsService.findAllReports();
    }

    @GetMapping("/api/reports/monthly")
    public List<MonthlyReport> getAllMonthlyReports() {
        return transitService.getTransitsFromCurrentMonth();
    }

    @RequestMapping(path = "/api/reports/dailySecond", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public Reports getDailyReport2(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return reportsService.addReportsModified(startDate, endDate);
    }

}



