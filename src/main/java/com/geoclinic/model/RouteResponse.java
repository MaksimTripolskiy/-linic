package com.geoclinic.model;

public class RouteResponse {
    private final double distanceMeters;
    private final double durationSeconds;
    private final String rawResponse;

    public RouteResponse(double distanceMeters, double durationSeconds, String rawResponse) {
        this.distanceMeters = distanceMeters;
        this.durationSeconds = durationSeconds;
        this.rawResponse = rawResponse;
    }

    public double getDistanceMeters() { return distanceMeters; }
    public double getDistanceKilometers() { return distanceMeters / 1000.0; }
    public double getDurationSeconds() { return durationSeconds; }
    public double getDurationMinutes() { return durationSeconds / 60.0; }
    public String getRawResponse() { return rawResponse; }

    public String getFormattedDistance() {
        if (distanceMeters < 1000) {
            return String.format("%.0f m", distanceMeters);
        } else {
            return String.format("%.2f km", getDistanceKilometers());
        }
    }

    public String getFormattedDuration() {
        if (durationSeconds < 60) {
            return String.format("%.0f sec", durationSeconds);
        } else if (durationSeconds < 3600) {
            return String.format("%.0f min", getDurationMinutes());
        } else {
            double hours = durationSeconds / 3600.0;
            double minutes = (durationSeconds % 3600) / 60.0;
            return String.format("%.0f h %.0f min", hours, minutes);
        }
    }

    @Override
    public String toString() {
        return String.format("Distance: %s, Duration: %s",
                getFormattedDistance(), getFormattedDuration());
    }
}