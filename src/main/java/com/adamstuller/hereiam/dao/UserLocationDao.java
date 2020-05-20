package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserLocationDao {

    int insertUserLocation(UserLocation userLocation);
    List<UserLocation> getAllUserLocations();
    int deleteUserLocation(String token);
    int updateUserLocation(String token, UserLocation userLocation);
    UserLocation getUserByToken(String token);

}
