package pl.skowrondariusz.TransportApplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.skowrondariusz.TransportApplication.service.TransitService;

@Controller
public class ExportController {


    @Autowired
    private TransitService transitService;

    @GetMapping("/download")
    public String download(Model model) {

        model.addAttribute("transits", transitService.findAll());
        return "";
    }

}
