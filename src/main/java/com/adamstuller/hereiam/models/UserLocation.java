package com.adamstuller.hereiam.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class UserLocation {

    @JsonProperty("token")
    private final String token;

    @JsonProperty("point")
    @NonNull
    private final Point point;

    public UserLocation(String token, Point point) {
        this.token = token;
        this.point = point;
    }

    public String getToken() {
        return token;
    }

    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "token='" + token + '\'' +
                ", latitute=" + point.getLatitude() +
                ", longitute=" + point.getLongitude() +
                '}';
    }
}
