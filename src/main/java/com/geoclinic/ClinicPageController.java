package com.geoclinic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
