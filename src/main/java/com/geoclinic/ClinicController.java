package com.geoclinic;

import com.geoclinic.dto.RegistrationRequest;
import com.geoclinic.model.Clinic;
import com.geoclinic.model.User;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
    @Autowired
    private UserService userService;


//    @GetMapping(value = "/getAllClinics")
//    public String getAllClinics() {
//        return clinicService.findClinics("dental").toString();
//    }


//    @PostMapping(value = "/createClinic")
//    public String createClinic(@RequestBody Clinic clinic) {
//        clinicService.createClinic(clinic);
//        return getAllClinics();
//    }

    @PostMapping(value = "/registerUser")
    public String registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerNewUser(request).toString();  // fixme
    }

//    @GetMapping(value = "/registerUser2")
//    public String registerUser(Model model) {
//        return "register-user";
//
//    }


}
