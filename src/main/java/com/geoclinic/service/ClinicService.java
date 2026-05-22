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

    public Clinic getClinicById(long id) {
        return clinicDAO.findById(id)
                .orElse(new Clinic());          // todo ap 5/10 handle optional
    }

    public List<Clinic> getAllClinics() {
        return clinicDAO.findAll();
    }

    public List<Clinic> findClinics(String type) {
        List<Clinic> clinics =  clinicDAO.findAll();
        System.out.println(clinics);
        return clinics;

    }

    public void createClinic(Clinic clinic) {

        clinicDAO.save(clinic);
    }           // todo clinic exists?


    public void deleteClinicById(long id) {
        clinicDAO.deleteById(id);   // todo ap 7/10 clinic does not exist?
    }

    public void updateClinic(Clinic clinic) { clinicDAO.save(clinic); }     // todo clinic exists?

    public List<Clinic> findClinicsInRadius(double lat, double lng, double radius) {
        List<Clinic> clinics = getAllClinics();

        List<Clinic> filteredClinics = clinics.stream().filter(clinic -> {

                                        double clinicLat = clinic.getLatitude();
                                        double clinicLng = clinic.getLongitude();

                                        // Дано: координаты центра круга
                                        double centerX = lat;
                                        double centerY = lng;

                                        // Вычисление разницы координат
                                        double deltaX = clinicLat - centerX;
                                        double deltaY = clinicLng - centerY;

                                        // Проверка: находится ли точка внутри круга
                                        if (Math.pow(clinicLat - centerX,2) + Math.pow(clinicLng - centerY,2) <= Math.pow(radius,2)) {
                                            return true;
                                        } else {
                                            return false;
                                        }
                                    }).toList();

        return filteredClinics;

    }
}
