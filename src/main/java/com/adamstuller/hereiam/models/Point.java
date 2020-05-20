package com.adamstuller.hereiam.models;

public class Point {
    private final float latitude;
    private final float longitude;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public Point(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
