//package pl.skowrondariusz.TransportApplication.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import pl.skowrondariusz.TransportApplication.service.TransitService;
//
//@Controller
//public class ExportController {
//
//    @Autowired
//    private TransitService transitService;
//
//    @RequestMapping(value = "/download", method = RequestMethod.GET)
//    public String download(Model model){
//
//        model.addAttribute("transitList", transitService.findAll());
//        return "";
//    }
//
//}
