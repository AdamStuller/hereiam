package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;

import java.util.UUID;

public interface UserLocationDao {

    int insetUserLocation(UUID uuid, UserLocation userLocation);

    default int insertUserLocation(UserLocation userLocation){
        UUID id = UUID.randomUUID();
        return this.insetUserLocation(id, userLocation);
    }
}
