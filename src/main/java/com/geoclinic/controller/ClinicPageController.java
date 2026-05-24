package com.geoclinic.controller;

import com.geoclinic.model.Clinic;
import com.geoclinic.model.Comment;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


@Controller
public class ClinicPageController {

    private ClinicService clinicService;
    private CommentService commentService;
    private String string;

    public ClinicPageController(ClinicService clinicService, CommentService commentService) {
        this.clinicService = clinicService;
        this.commentService = commentService;
    }


    @GetMapping
    public String registerUser() {
        return "register-user";
    }

    @GetMapping("/login")
    public String getLogin() {
        return string;
    }

    @GetMapping("/createClinic")
    public String getCreateClinicPage() {
        return "create-clinic-map";
    }

    @GetMapping("/admin/manageClinics")
    public String listClinics(Model model) {
        List<Clinic> clinics = clinicService.getAllClinics();
        model.addAttribute("clinics", clinics);
        return "manage-clinics";
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

    // todo ap 5/10 if no such clinic id, need 404 page
    @GetMapping(value = "/getClinic")
    public String getClinic(@RequestParam(value = "id") Long id, Model model) {

        Clinic clinic = clinicService.getClinicById(id);
        List<Comment> comments = commentService.getApprovedCommentsByClinicId(id);

        model.addAttribute("clinic", clinic);
        model.addAttribute("reviews", comments);

        return "clinic-page";
    }


    @GetMapping(value = "/admin/getAllClinics")
    public String getAllClinicsAsAdmin(Model model) {
        List<Clinic> clinicsList = clinicService.getAllClinics();


        ObjectMapper mapper = new ObjectMapper();

        String clinicsJson = mapper.writeValueAsString(clinicsList);
        model.addAttribute("clinicsJson", clinicsJson);


        return "map-view-clickable-admin";

    }

    @GetMapping(value = "/user/getAllClinics")
    public String getAllClinicsAsUser(Model model) {
        List<Clinic> clinicsList = clinicService.getAllClinics();


        ObjectMapper mapper = new ObjectMapper();

        String clinicsJson = mapper.writeValueAsString(clinicsList);
        model.addAttribute("clinicsJson", clinicsJson);


        return "map-view-clickable2";

    }

    @GetMapping(value = "/getMap")
    public String getMap(Model model) {
        return "map-view";
    }

}
