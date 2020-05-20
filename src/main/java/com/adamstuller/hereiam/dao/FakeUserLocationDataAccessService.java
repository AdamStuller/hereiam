package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserLocationDataAccessService implements UserLocationDao{

    private static List<UserLocation> DB = new ArrayList<>();


    @Override
    public int insertUserLocation(UserLocation userLocation) {
        DB.add(userLocation);
        return 0;
    }

    @Override
    public List<UserLocation> getAllUserLocations() {
        return DB;
    }
}
