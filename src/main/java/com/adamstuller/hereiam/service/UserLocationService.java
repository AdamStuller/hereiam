package com.adamstuller.hereiam.service;

import com.adamstuller.hereiam.dao.UserLocationDao;
import com.adamstuller.hereiam.models.UserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserLocationService {

    private final UserLocationDao userDao;

    @Autowired
    public UserLocationService(@Qualifier("fake") UserLocationDao userDao) {
        this.userDao = userDao;
    }

    public int addUserLocation(UserLocation userLocation){
        return this.userDao.insertUserLocation(userLocation);
    }
}
