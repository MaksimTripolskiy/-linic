package com.geoclinic.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@Component
public class Handler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

//        if (roles.contains("ROLE_ADMIN")) {           TODO restore
//            response.sendRedirect("/createClinic");
//        } else if (roles.contains("ROLE_USER")) {
//            response.sendRedirect("/getAllClinics");
//        } else {
////            response.sendRedirect("/dashboard");
//
//            System.out.println("""
//
//
//                    WHO IS THIS USER???
//
//
//                    """);
//        }
    }
}