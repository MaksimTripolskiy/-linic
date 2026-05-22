package com.geoclinic.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;


@Data
@Entity
@Table(name="clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;;
    @Column(name="phone")
    private String phone;
    @Column(name="type")
    private String type;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;
//    @Column(name="open_time")
//    private LocalTime openTime;
//    @Column(name="close_time")
//    private LocalTime closeTime;
    @Column(name="work_hours")
    private String workHours;


    @OneToMany(mappedBy = "clinic",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Comment> comments;


//    @OneToOne
//    private Coordinates coordinates;

    public Clinic() {}

    public Clinic(double latitude, double longitude) {      // todo remove?
        this.latitude = latitude;
        this.longitude = longitude;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
}
