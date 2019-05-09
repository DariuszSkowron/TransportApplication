package pl.skowrondariusz.TransportApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.service.TransitService;

@Controller
public class TransitController {

    @Autowired
    TransitService transitService;

    @Autowired
    public TransitController(TransitService transitService) {
        this.transitService = transitService;
    }

    @GetMapping("/transit")
    public String showFormTransit(ModelMap modelMap) {
        Transit transitAttr = new Transit();
        modelMap.addAttribute("transitAttribute", transitAttr);
        return "transitForm";
    }

    @GetMapping("/transits")
    public String showTransits(ModelMap modelMap) {
        modelMap.addAttribute("transits", transitService.findAll());
        return "showTransits";
    }

    @PostMapping("/addtransit")
    public String addTransit(@ModelAttribute Transit transitAttribute, ModelMap modelMap) {
        transitService.save(transitAttribute);
        transitService.calculateDistance(transitAttribute);
        transitService.save(transitAttribute);
        modelMap.addAttribute("transitsAttribute", transitService.findAll());
        return "showTransit";
    }


}
