package com.geoclinic.controller;

import com.geoclinic.dto.RegistrationRequest;
import com.geoclinic.model.Clinic;
import com.geoclinic.model.RouteCoordinates;
import com.geoclinic.model.RouteResponse;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.RouteService;
import com.geoclinic.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ClinicController {

    @Autowired
    private RouteService routeService;
    @Autowired      // fixme
    private ClinicService clinicService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/getAllClinics")           // todo ap 3/10 remove
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
        userService.registerNonAdminUser(request).toString();  // fixme
        return "redirect:/page/getAllClinics";
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

    @GetMapping("/api/clinics/nearby")
    public String findClinicsInRadius(@RequestParam(name = "lat") double lat,
                                      @RequestParam(name = "lng") double lng,
                                      @RequestParam(name = "radius") double radius) {
        List<Clinic> clinicsNearPoint = clinicService.findClinicsInRadius(lat, lng, radius);

        return clinicsNearPoint.toString();


    }

//    @PostMapping("/delete/{id}")
//    public String deleteClinic(@PathVariable(name = "id") Long id) {
//            clinicService.deleteClinicById(id);
//            return "redirect:/admin/manageClinics";
//    }

    @PostMapping("/delete/{id}")
    public void deleteClinic(@PathVariable(name = "id") Long id, HttpServletResponse response) {
        clinicService.deleteClinicById(id);
        try {
            response.sendRedirect("/admin/manageClinics");
        } catch (IOException e) {
            throw new RuntimeException(e);     // todo ap 5/10
        }
    }


//    @GetMapping(value = "/registerUser2")
//    public String registerUser(Model model) {
//        return "register-user";
//
//    }


}
