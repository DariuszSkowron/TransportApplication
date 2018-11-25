package pl.skowrondariusz.TransportApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;
import pl.skowrondariusz.TransportApplication.service.TransitService;

@Controller
public class TransitController {

    TransitService transitService;
    @Autowired
    public  TransitController(TransitService transitService){
        this.transitService = transitService;
    };

    @GetMapping("/transit")
    public String showFormTransit(ModelMap modelMap){
        Transit transitAttr = new Transit();
        modelMap.addAttribute("transitAttribute", transitAttr);
        return "transitForm";
    }

    @PostMapping("/addtransit")
    public String addTransit(@ModelAttribute Transit transitAttribute, ModelMap modelMap){
//
        transitService.save(transitAttribute);
        transitService.calculateDistance(transitAttribute);
        modelMap.addAttribute("transitsAttribute", transitService.findAll());
        return "showTransit";

    }

}
