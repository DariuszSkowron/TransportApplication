package pl.skowrondariusz.TransportApplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.skowrondariusz.TransportApplication.model.Reports;
import pl.skowrondariusz.TransportApplication.service.ReportsService;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import java.time.LocalDate;

@Controller
public class ReportsController {


    @Autowired
    TransitService transitService;

    @Autowired
    ReportsService reportsService;

    @GetMapping("/reports/monthly")
    public String showForm(ModelMap modelMap){
        modelMap.addAttribute("reports", transitService.getTransitsFromCurrentMonth() );

        return "showMonthlyReport";
    }

//    @PostMapping("/reports/daily")
//    public String addDailyReport(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, ModelMap modelMap){
//        modelMap.addAttribute("dailyReport",  reportsService.addReportsModified(startDate, endDate));
//        return "showReports";
//    }
//
    @GetMapping("/reports/daily")
    public String addDailyReport(ModelMap modelMap) {
        Reports reportsAttr = new Reports();
        modelMap.addAttribute("dailyReport", reportsAttr);
//        return new ModelAndView("dailyReportForm", modelMap);
        return "dailyReportForm";

    }

    @PostMapping("/addDailyReport")
    public String addDailyReport1(@RequestParam String startDate, @RequestParam String endDate, ModelMap modelMap){
       LocalDate newStartDate =  LocalDate.parse(startDate);
       LocalDate newEndDate = LocalDate.parse(endDate);
//       Reports reports = new Reports();
//        reports.setTotalDistance(100L);
//        reports.setTotalPrice(200L);
//       reportsService.addReportsModified(newStartDate, newEndDate);
     modelMap.addAttribute("dailyReport", reportsService.addReportsModified(newStartDate, newEndDate));
     return "showReport";
    }
}
//