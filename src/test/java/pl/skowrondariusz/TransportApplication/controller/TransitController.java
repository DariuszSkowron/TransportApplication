package pl.skowrondariusz.TransportApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.skowrondariusz.TransportApplication.model.Transit;
import pl.skowrondariusz.TransportApplication.repository.TransitRepository;

public class TransitController {


    @Autowired
    TransitRepository transitRepository = new TransitRepository();

    @GetMapping("/transit")
    public ModelAndView showform(ModelMap modelMap){
        modelMap.addAttribute("transit", new Transit());
        return new ModelAndView("addtransit", modelMap);
    }

    @PostMapping("/transit")
    public String addTransit(@ModelAttribute Transit transit, ModelMap modelMap){
        transitRepository.calculateDistance(transit);
        transitRepository.addTransit(transit);
        modelMap.addAttribute("transit", transit);
        return "redirect:showtransit";

    }
}
