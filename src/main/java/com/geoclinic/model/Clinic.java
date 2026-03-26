package com.geoclinic.model;

import jakarta.persistence.*;
import lombok.Data;



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
    @OneToOne
    private Coordinates coordinates;

    public Clinic() {}
}
