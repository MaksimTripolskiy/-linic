package com.geoclinic.controller;

import com.geoclinic.dto.RegistrationRequest;
import com.geoclinic.model.Clinic;
import com.geoclinic.model.RouteCoordinates;
import com.geoclinic.model.RouteResponse;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.RouteService;
import com.geoclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClinicController {

    @Autowired
    private RouteService routeService;
    @Autowired      // fixme
    private ClinicService clinicService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/getAllClinics")
    public String getAllClinics() {
        return clinicService.getAllClinics().toString();
    }


//    @PostMapping(value = "/createClinic")
//    public String createClinic(@RequestBody Clinic clinic) {
//        clinicService.createClinic(clinic);
//        return getAllClinics();
//    }


    @PostMapping(value = "/registerUser")
    public String registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerNewUser(request).toString();  // fixme
    }

    @PostMapping(value = "/api/route")
    public String getRoute(@RequestBody RouteCoordinates coords) {
        RouteResponse response = null;
        try {
            response = routeService.getRoute(coords.getStartLng(),coords.getStartLat(), coords.getDestLng(), coords.getDestLat());
        } catch (Exception e) {
            throw new RuntimeException(e);      // todo ap 8/10 fixme
        }
        return response.getRawResponse();
    }

//    @GetMapping(value = "/registerUser2")
//    public String registerUser(Model model) {
//        return "register-user";
//
//    }


}
