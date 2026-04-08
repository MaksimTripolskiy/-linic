package com.geoclinic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name="clinics")
public class Clinic {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="type")
    private String type;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;


    private List<Comment> comments;
//    @OneToOne
//    private Coordinates coordinates;

    public Clinic() {}

    public Clinic(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
