package com.adamstuller.hereiam.models;

import java.util.UUID;

public class UserLocation {

    private final String token;
    private float langitute;
    private float longtitute;

    public UserLocation(String token, float langitute, float longtitute) {
        this.token = token;
        this.langitute = langitute;
        this.longtitute = longtitute;
    }

    public String getToken() {
        return token;
    }

    public float getLangitute() {
        return langitute;
    }

    public void setLangitute(float langitute) {
        this.langitute = langitute;
    }

    public float getLongtitute() {
        return longtitute;
    }

    public void setLongtitute(float longtitute) {
        this.longtitute = longtitute;
    }

    @Override
    public String toString() {
        return "UserLocation{" +
                "token='" + token + '\'' +
                ", langitute=" + langitute +
                ", longtitute=" + longtitute +
                '}';
    }
}
