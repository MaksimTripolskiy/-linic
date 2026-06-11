package com.geoclinic.controller;

import com.geoclinic.model.Profile;
import com.geoclinic.model.User;
import com.geoclinic.service.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfileController {

    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/createProfile")
    public void createProfile(@RequestBody Profile profile) {
        profileService.createProfile(profile);
    }

    @GetMapping("/getProfile")
    public String getProfile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();


        Map<String, Object> profileDto = new HashMap<>();
        Profile profile = user.getProfile();

//        Hibernate.initialize(profile.getFavoriteClinics());

//        profileDto.put("id", profile.getId());
//        profileDto.put("age", profile.getAge());
//        profileDto.put("name", profile.getName());
//        profileDto.put("gender", profile.getGender());
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        String profileJson = mapper.writeValueAsString(profileDto);
//        model.addAttribute("profile", profileJson);

        model.addAttribute("profile", profile);

        return "user-profile";
    }
}
