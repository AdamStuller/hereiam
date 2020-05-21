package com.adamstuller.hereiam.dao;

import com.adamstuller.hereiam.models.UserLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * Dummy implementation of data access service. Exposing full interface.
 * Internally represented by ArrayList DB.
 */
@Repository("fakeDao")
public class FakeUserLocationDataAccessService implements UserLocationDao{

    Logger logger = LoggerFactory.getLogger(FakeUserLocationDataAccessService.class);
    /**
     * Dummy  storage implementation.
     */
    private static List<UserLocation> DB = new ArrayList<>();


    /**
     * Inserts new user location into dummy data storage.
     * @param userLocation User location to be inserted
     * @return
     */
    @Override
    public int insertUserLocation(UserLocation userLocation) {
        logger.info(userLocation.toString());
        DB.add(userLocation);
        return 0;
    }

    /**
     * Dummy get all implementation.
     * @return Whole dummy storage
     */
    @Override
    public List<UserLocation> getAllUserLocations() {
        return DB;
    }

    /**
     * Dummy delete implementation.
     * @param token Unique server side id. Determines which user location to remove.
     * @return
     */
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

    /**
     * Dummy update representation.
     * @param token Unique server side id. Determines which user location to update.
     * @param userLocation New user location, that replaces old on its place in dummy storage.
     * @return
     */
    @Override
    public int updateUserLocation(String token, UserLocation userLocation) {
//        UserLocation originalUserLocation = this.getUserByToken(token);
//        if(originalUserLocation != null){
//            int updateIndex = DB.indexOf(originalUserLocation);
//            if(updateIndex >= 0){
//                logger.info(Integer.toString(updateIndex));
//                logger.info(userLocation.toString());
//                logger.info(Arrays.toString(DB.toArray()));
//                DB.set(
//                        updateIndex,
//                        new UserLocation(
//                                userLocation.getToken(),
//                                new Point(
//                                        userLocation.getPoint().getLatitude(),
//                                        userLocation.getPoint().getLongitude()
//                                )
//                        )
//                );
//                logger.info(Arrays.toString(DB.toArray()));
//                return 1;
//            }
//            return 0;
//        }
        return 0;
    }

    /**
     * Dummy get by token implementation.
     * @param token Unique server side id. Determines which user location to update.
     * @return Required user location. Has same token.
     */
    @Override
    public UserLocation getUserByToken(String token) {
        return DB.stream()
                .filter(userLocation -> userLocation.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }

    /**
     * Dummy implementation. Should return only points from within distance.
     * @param center Center of circle.
     * @param radius Maximal distance
     * @return Whole  storage.
     */
    @Override
    public List<UserLocation> getPointsWithinRadius(UserLocation center, int radius) {
        // Just dummy solution, in FakeDao, only few points are present.
        return DB;
    }
}
