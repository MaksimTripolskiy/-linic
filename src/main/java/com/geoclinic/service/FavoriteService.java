package com.geoclinic.service;

import com.geoclinic.model.Clinic;
import com.geoclinic.model.Favorite;
import com.geoclinic.model.Profile;
import com.geoclinic.repository.ClinicDAO;
import com.geoclinic.repository.FavoriteDAO;
import com.geoclinic.repository.ProfileDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

//    private ClinicDAO clinicDAO;
//    private ProfileDAO profileDAO;
    private FavoriteDAO favoriteDAO;

//    public FavoriteService(ProfileDAO profileDAO, ClinicDAO clinicDAO) {
//        this.profileDAO = profileDAO;
//        this.clinicDAO = clinicDAO;
//    }


    public FavoriteService(FavoriteDAO favoriteDAO) {
        this.favoriteDAO = favoriteDAO;
    }

    public void createFavorite(Long clinicId, Long profileId) {
//        Clinic clinic = clinicDAO.findById(clinicId).orElseThrow();
//        Profile profile = profileDAO.findById(profileId).orElseThrow();
//        clinic.getProfiles().add(profile);
//
//        clinicDAO.save(clinic);

        favoriteDAO.addFavorite(clinicId, profileId);

    }

    public List<Long> getAllFavoriteClinicIdsForProfileId(Long profileId) {
        return favoriteDAO.findAll().stream()
                                    .filter(favorite -> favorite.getProfileId() == profileId)
                                    .map(favorite -> favorite.getClinicId())
                                    .toList();
    }

}
