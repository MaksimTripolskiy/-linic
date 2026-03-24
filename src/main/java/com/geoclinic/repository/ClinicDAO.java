package com.geoclinic.repository;


import com.geoclinic.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicDAO extends JpaRepository<Clinic, Long> {

}
