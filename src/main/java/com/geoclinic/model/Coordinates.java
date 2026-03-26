package com.geoclinic.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data           // todo toString() & equals() needed?
@Entity
@Table(name="coordinates")
public class Coordinates {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;

}
