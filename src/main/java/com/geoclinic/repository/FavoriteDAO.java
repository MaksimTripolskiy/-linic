package com.geoclinic.repository;

import com.geoclinic.model.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Transient;

@Repository
public interface FavoriteDAO extends JpaRepository<Favorite, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO favorite_clinics (clinic_id, profile_id) VALUES (:clinicId, :profileId)",
            nativeQuery = true)
    void addFavorite(@Param("clinicId") Long clinicId, @Param("profileId") Long profileId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM favorite_clinics WHERE clinic_id = :clinicId AND profile_id = :profileId",
            nativeQuery = true)
    void removeFavorite(@Param("clinicId") Long clinicId, @Param("profileId") Long profileId);

    boolean existsByClinicIdAndProfileId(Long clinicId, Long profileId);
}