package com.geoclinic.repository;

import com.geoclinic.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDAO extends JpaRepository<Profile,Long> {
}
