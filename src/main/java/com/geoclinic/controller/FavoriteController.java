package com.geoclinic.controller;

import com.geoclinic.service.FavoriteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FavoriteController {

    private FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/createFavorite")  // POST вместо GET
    public void createFavorite(
            @RequestParam(name = "clinicId") Long clinicId,
            @RequestParam(name = "profileId") Long profileId, HttpServletResponse response) {
        favoriteService.createFavorite(clinicId, profileId);
        try {
            response.sendRedirect("/user/getAllClinics");
        } catch (IOException e) {
            throw new RuntimeException(e);     // todo ap 5/10
        }
    }

    @GetMapping("/removeFavorite")
    public void removeFavorite(
            @RequestParam(name = "clinicId") Long clinicId,
            @RequestParam(name = "profileId") Long profileId, HttpServletResponse response) {
        favoriteService.removeFavorite(clinicId, profileId);
        try {
            response.sendRedirect("/getProfile");
        } catch (IOException e) {
            throw new RuntimeException(e);     // todo ap 5/10
        }
    }
}
