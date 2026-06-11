package com.geoclinic.service;

import com.geoclinic.model.Profile;
import com.geoclinic.repository.ProfileDAO;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private ProfileDAO profileDAO;

    public ProfileService(ProfileDAO profileDAO) {
        this.profileDAO = profileDAO;
    }

    public void createProfile(Profile profile) {
        profileDAO.save(profile);
    }
}
