package com.adamstuller.hereiam.service;
import com.adamstuller.hereiam.dao.UserLocationDao;
import com.adamstuller.hereiam.models.UserLocation;
import com.vividsolutions.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Place for business logic. Accessed data trough data access service and can do operations on it.
 * In this case, however only calls methods of data access service.
 */
@Service
public class UserLocationService {

    private final UserLocationDao userDao;

    // postgres DAO is used.
    @Autowired
    public UserLocationService(@Qualifier("postgres") UserLocationDao userDao) {
        this.userDao = userDao;
    }

    public int addUserLocation(UserLocation userLocation){
        return this.userDao.insertUserLocation(userLocation);
    }

    public List<UserLocation> getAllUserLocation(){
        return this.userDao.getAllUserLocations();
    }

    public int deleteUserLocation(String token){
        return this.userDao.deleteUserLocation(token);
    }

    public int updateUserLocation(String token, UserLocation userLocation){
        return this.userDao.updateUserLocation(token, userLocation);
    }

    public UserLocation getByToken(String token){
        return this.userDao.getUserByToken(token);
    }

    public List<UserLocation> getPointsWithinRadius(UserLocation center, int radius){
        return this.userDao.getPointsWithinRadius(center, radius);
    }
}
