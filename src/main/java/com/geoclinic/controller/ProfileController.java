package com.geoclinic.controller;

import com.geoclinic.model.Clinic;
import com.geoclinic.model.Profile;
import com.geoclinic.model.User;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.FavoriteService;
import com.geoclinic.service.ProfileService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

@Controller
public class ProfileController {

    private ProfileService profileService;
    private FavoriteService favoriteService;
    private ClinicService clinicService;

    public ProfileController(ProfileService profileService, FavoriteService favoriteService, ClinicService clinicService) {
        this.profileService = profileService;
        this.favoriteService = favoriteService;
        this.clinicService = clinicService;
    }

    @PostMapping("/createProfile")
    public void createProfile(@RequestBody Profile profile) {
        profileService.createProfile(profile);
    }

    @GetMapping("/getProfile")
    public String getProfile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Profile profile = user.getProfile();

        List<Long> favoriteClinicIds = favoriteService.getAllFavoriteClinicIdsForProfileId(profile.getId());


        List<Map<String, Object>> favoriteClinicDTOs = new ArrayList<>();
        for (Long clinicId : favoriteClinicIds) {
            Clinic clinic = clinicService.getClinicById(clinicId);

            Map<String, Object> clinicDto = new HashMap<>();
            clinicDto.put("id", clinic.getId());
            clinicDto.put("name", clinic.getName());
            clinicDto.put("address", clinic.getAddress());
            clinicDto.put("phone", clinic.getPhone());
            // Add only the fields you need in the view
            // DON'T add lazy-loaded collections

            favoriteClinicDTOs.add(clinicDto);
        }

        // Create Profile DTO
        Map<String, Object> profileDto = new HashMap<>();
        profileDto.put("id", profile.getId());
        profileDto.put("age", profile.getAge());
        profileDto.put("name", profile.getName());
        profileDto.put("gender", profile.getGender());
        profileDto.put("favoriteClinics", favoriteClinicDTOs);  // Use DTOs, not entities

        model.addAttribute("profile", profileDto);

        return "user-profile";
    }
}
