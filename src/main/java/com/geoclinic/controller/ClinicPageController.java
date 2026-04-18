package com.geoclinic.controller;

import com.geoclinic.model.Clinic;
import com.geoclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ClinicPageController {

    private ClinicService clinicService;

    public ClinicPageController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/registerUser2")
    public String registerUser() {
        return "register-user";

    }

    @GetMapping("/createClinic")
    public String getCreateClinicPage() {
        return "create-clinic-map";

    }

    @PostMapping("/createClinic")
    public String createClinic(@ModelAttribute Clinic clinic, Model model) {
        try {
            // Сохранить клинику
            clinicService.createClinic(clinic);
            model.addAttribute("message", "Clinic created successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "create-clinic-map"; // вернуть ту же страницу с сообщением
    }

    @GetMapping(value = "/page/getAllClinics")
    public String getAllClinics(Model model) {
        List<Clinic> clinicsList = clinicService.getAllClinics();


        ObjectMapper mapper = new ObjectMapper();

        String clinicsJson = mapper.writeValueAsString(clinicsList);
        model.addAttribute("clinicsJson", clinicsJson);

        System.out.println(clinicsJson);

        return "map-view";

    }

    @GetMapping(value = "/getMap")
    public String getMap(Model model) {
        return "map-view";
    }

}
