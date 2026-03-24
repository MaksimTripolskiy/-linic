package com.geoclinic.model;


import lombok.Data;

@Data           // todo toString() & equals() needed?
public class Coordinates {

    private String address;
    private double x;
    private double y;

}
