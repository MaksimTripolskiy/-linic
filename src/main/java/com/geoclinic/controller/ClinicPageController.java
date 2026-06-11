package com.geoclinic.controller;

import com.geoclinic.dto.RegistrationRequest;
import com.geoclinic.model.Clinic;
import com.geoclinic.model.Comment;
import com.geoclinic.model.Profile;
import com.geoclinic.model.User;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.CommentService;
import com.geoclinic.service.ProfileService;
import com.geoclinic.service.UserService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


@Controller
public class ClinicPageController {

    private ClinicService clinicService;
    private CommentService commentService;
    @Autowired
    private UserService userService;
    private ProfileService profileService;
    private String string;

    public ClinicPageController(ClinicService clinicService, CommentService commentService, ProfileService profileService) {
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

//    @GetMapping("/createClinic")
//    public String getCreateClinicPage() {
//        return "create-clinic-map";
//    }

    @PostMapping("/registerUser")
    public String registerUser(@RequestBody RegistrationRequest request) {
        User user = userService.registerNonAdminUser(request);

        return "redirect:/user/getAllClinics";
    }


    @GetMapping("/createClinic")
    public String showClinicForm(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("clinic", new Clinic()); // пустой объект
        } else {
            Clinic existingClinic = clinicService.getClinicById(id); // загружаем клинику из БД
            model.addAttribute("clinic", existingClinic);
        }
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


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();


        Map<String, Object> profileDto = new HashMap<>();
        Profile profile = user.getProfile();

//        Hibernate.initialize(profile.getFavoriteClinics());

        profileDto.put("id", profile.getId());
        profileDto.put("age", profile.getAge());
        profileDto.put("name", profile.getName());
        profileDto.put("gender", profile.getGender());



        String profileJson = mapper.writeValueAsString(profileDto);
        model.addAttribute("profile", profileJson);


        return "map-view-clickable2";

    }

    @GetMapping(value = "/getMap")
    public String getMap(Model model) {
        return "map-view";
    }

}
