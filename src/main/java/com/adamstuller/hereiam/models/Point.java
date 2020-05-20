package com.adamstuller.hereiam.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

public class Point {
    @JsonProperty("latitude")
    @NonNull
    private final float latitude;
    @JsonProperty("longitude")
    @NonNull
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
