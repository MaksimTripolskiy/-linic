package com.geoclinic.controller;

import com.geoclinic.model.Clinic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClinicPageController {


    @GetMapping("/registerUser2")
    public String registerUser() {
        return "register-user";

    }

    @GetMapping("/createClinic")
    public String getCreateClinicPage() {
        return "create-clinic";

    }

    @GetMapping(value = "/getAllClinics")
    public String getAllClinics(Model model) {
//        List<Clinic> clinicsList =  clinicService.findClinics("dental");
        List<Clinic> clinicsList = new ArrayList<>();
        clinicsList.add(new Clinic(55.755169,37.594524));
        clinicsList.add(new Clinic(55.753263,37.594504));
        model.addAttribute("clinics", clinicsList);
        return "map-view";

    }

    @GetMapping(value = "/getMap")
    public String getMap(Model model) {
        return "map-view";
    }

}
