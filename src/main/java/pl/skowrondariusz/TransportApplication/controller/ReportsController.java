package pl.skowrondariusz.TransportApplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.skowrondariusz.TransportApplication.model.MonthlyReport;
import pl.skowrondariusz.TransportApplication.service.TransitService;

import java.util.List;

@Controller
public class ReportsController {


    @Autowired
    TransitService transitService;

    @GetMapping("/reports/monthly")
    public String showForm(ModelMap modelMap){
        modelMap.addAttribute("reports", transitService.getTransitsFromCurrentMonth() );

        return "showMonthlyReport";
    }
}
