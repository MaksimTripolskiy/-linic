package com.geoclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FavoriteId implements Serializable {

    @Column(name = "clinic_id")
    private Long clinicId;

    @Column(name = "profile_id")
    private Long profileId;
}