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
    public TransitController(TransitService transitService){
        this.transitService = transitService;
    }

    @GetMapping("/transit")
    public ModelAndView showform(ModelMap modelMap){
        modelMap.addAttribute("transit", new Transit());
        return new ModelAndView("addtransit", modelMap);
    }

    @PostMapping("/transit")
    public String addTransit(@ModelAttribute Transit transit, ModelMap modelMap){
        transitService.calculateDistance(transit);
        transitService.addTransit(transit);
        modelMap.addAttribute("transit", transit);
        return "redirect:showTransit";

    }


}
