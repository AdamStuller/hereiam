package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;

import java.util.List;

public interface UserLocationDao {

    int insertUserLocation(UserLocation userLocation);
    List<UserLocation> getAllUserLocations();
    int deleteUserLocation(String token);
    int updateUserLocation(String token, UserLocation userLocation);
    UserLocation getUserByToken(String token);
    List<UserLocation> getPointsWithinRadius(UserLocation center, int radius);

}
