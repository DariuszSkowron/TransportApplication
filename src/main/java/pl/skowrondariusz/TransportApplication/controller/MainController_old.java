package pl.skowrondariusz.TransportApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController_old {



//    @RequestMapping(method = RequestMethod.GET, value = "/")
//    public String index( ){
//        modelMap.addAttribute("message", "hello academy");
//        return "index"; //Thymeleaf //file path
//    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}