package com.geoclinic.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "favorite_clinics")
@IdClass(FavoriteId.class)
public class Favorite {

    @Id
    @Column(name = "clinic_id")
    private Long clinicId;

    @Id
    @Column(name = "profile_id")
    private Long profileId;
}
