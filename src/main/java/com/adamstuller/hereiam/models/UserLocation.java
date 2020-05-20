package com.adamstuller.hereiam.models;

public class UserLocation {

    private final String token;
    private Point point;

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
                ", langitute=" + point.getLatitude() +
                ", longtitute=" + point.getLongitude() +
                '}';
    }
}
