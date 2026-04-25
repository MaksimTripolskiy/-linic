package com.geoclinic.service;

import com.geoclinic.model.RouteResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RouteService {


    /**
     * Get route between point A and point B
     * @param startLon Longitude of start point
     * @param startLat Latitude of start point
     * @param endLon Longitude of end point
     * @param endLat Latitude of end point
     * @return RouteResponse with distance and duration
     */

    private static final String BASE_URL = "https://api.openrouteservice.org/v2/directions/driving-car";            /// todo car or person?
    private final String apiKey;
    private final HttpClient httpClient;
    private final Gson gson;

    public RouteService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }



    public RouteResponse getRoute(double startLon, double startLat, double endLon, double endLat) throws Exception {
        String url = BASE_URL + "?start=" + startLon + "," + startLat +
                "&end=" + endLon + "," + endLat + "&api_key=" + apiKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {     // todo what do with responses?
            throw new Exception("API error: " + response.statusCode() + " - " + response.body());
        }

        return parseResponse(response.body());
    }

    private RouteResponse parseResponse(String jsonResponse) {
        JsonObject root = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray features = root.getAsJsonArray("features");

        JsonObject properties = features.get(0).getAsJsonObject()
                .getAsJsonObject("properties");
        JsonObject summary = properties.getAsJsonObject("summary");

        double distance = summary.get("distance").getAsDouble(); // meters
        double duration = summary.get("duration").getAsDouble(); // seconds

        return new RouteResponse(distance, duration, jsonResponse); // todo jsonResponse as param3 correct?
    }

}
