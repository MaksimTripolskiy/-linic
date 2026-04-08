package com.geoclinic.service;

import com.geoclinic.model.Clinic;
import com.geoclinic.repository.ClinicDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {

    private ClinicDAO clinicDAO;

    public ClinicService(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    public List<Clinic> findClinics(String type) {
        List<Clinic> clinics =  clinicDAO.findAll();
        System.out.println(clinics);
        return clinics;

    }

    public void createClinic(Clinic clinic) {
        clinicDAO.save(clinic);
    }

    public void updateClinic(Clinic clinic) { clinicDAO.save(clinic); }     // todo
}
