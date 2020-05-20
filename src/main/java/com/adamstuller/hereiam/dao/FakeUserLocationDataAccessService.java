package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository("fakeDao")
public class FakeUserLocationDataAccessService implements UserLocationDao{

    Logger logger = LoggerFactory.getLogger(FakeUserLocationDataAccessService.class);
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

    @Override
    public int deleteUserLocation(String token) {
        UserLocation userLocation = this.getUserByToken(token);
        if(userLocation != null){
            System.out.println(DB);
            DB.remove(userLocation);
            System.out.println(DB);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateUserLocation(String token, UserLocation userLocation) {
        UserLocation originalUserLocation = this.getUserByToken(token);
        if(originalUserLocation != null){
            int updateIndex = DB.indexOf(originalUserLocation);
            if(updateIndex >= 0){
                logger.info(Integer.toString(updateIndex));
                logger.info(userLocation.toString());
                logger.info(Arrays.toString(DB.toArray()));
                DB.set(
                        updateIndex,
                        new UserLocation(
                                userLocation.getToken(),
                                userLocation.getLangitute(),
                                userLocation.getLongtitute()
                        )
                );
                logger.info(Arrays.toString(DB.toArray()));
                return 1;
            }
            return 0;
        }
        return 0;
    }

    @Override
    public UserLocation getUserByToken(String token) {
        return DB.stream()
                .filter(userLocation -> userLocation.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }
}
