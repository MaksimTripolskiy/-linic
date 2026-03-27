package com.geoclinic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClinicPageController {


    @GetMapping(value = "/registerUser2")
    public String registerUser(Model model) {
        return "register-user";
    }
}
