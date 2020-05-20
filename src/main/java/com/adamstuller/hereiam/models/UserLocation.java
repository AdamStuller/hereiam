package com.adamstuller.hereiam.models;

import java.util.UUID;

public class UserLocation {

    private final String token;
    private final UUID uuid;
    private float langitute;
    private float longtitute;

    public UserLocation(String token, UUID uuid, float langitute, float longtitute) {
        this.token = token;
        this.uuid = uuid;
        this.langitute = langitute;
        this.longtitute = longtitute;
    }

    public UUID getUuid() {
        return uuid;
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


}
