package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fake")
public class FakeUserLocationDataAccessService implements UserLocationDao{

    private static List<UserLocation> DB = new ArrayList<>();


    @Override
    public int insetUserLocation(UUID uuid, UserLocation userLocation) {
        DB.add(new UserLocation(userLocation.getToken(), uuid, userLocation.getLangitute(), userLocation.getLongtitute()));
        return 0;
    }
}
