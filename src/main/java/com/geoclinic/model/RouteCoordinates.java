package com.geoclinic.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RouteCoordinates {

    private double startLat;
    private double startLng;
    private double destLat;
    private double destLng;


}

// const requestBody = {
//startLat: userLocation.lat,
//startLng: userLocation.lng,
//endLat: destLat,
//endLng: destLng
//          };
