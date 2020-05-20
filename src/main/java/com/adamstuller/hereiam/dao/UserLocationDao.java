package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;

import java.util.List;
import java.util.UUID;

public interface UserLocationDao {

    int insertUserLocation(UserLocation userLocation);
    List<UserLocation> getAllUserLocations();

}
