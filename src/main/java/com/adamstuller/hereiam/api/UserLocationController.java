package com.adamstuller.hereiam.api;

import com.adamstuller.hereiam.models.UserLocation;
import com.adamstuller.hereiam.service.UserLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for user location resource.
 */
@RequestMapping("api/v1/userLocation")
@RestController
public class UserLocationController {

    /**
     * Responsible for business logic. Accesses Dao.
     */
    private final UserLocationService userLocationService;
    Logger logger = LoggerFactory.getLogger(UserLocationController.class);

    @Autowired
    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    /**
     * @param req Map<String, String> Json object, hard to represent and deserialize into
     *            user location. Must contain token: String, latitude: Float, longitude: Float.
     *            Req is mapped to user location and passed to business logic.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserLocation(@Validated @RequestBody Map<String, String> req){
        // Processing of request
        final String token = req.get("token");
        final Float lan = Float.parseFloat(req.get("latitude"));
        final Float lon = Float.parseFloat(req.get("longitude"));
        UserLocation userLocation= new UserLocation(token, lan, lon);

        logger.info("Add user location accepted");
        logger.info( userLocation.toString());

        userLocationService.addUserLocation(userLocation);
    }

    /**
     * Gets all user locations.
     * @return All user locations.
     */
    @GetMapping
    public List<UserLocation> getAllUserLocations(){
        logger.info("All user locations accepted!");
        return userLocationService.getAllUserLocation();
    }

    /**
     * Gets user location with specific token.
     * @param token Unique client side identifier.
     * @return User location found by token.
     */
    @GetMapping( path = "{token}")
    public UserLocation getByToken(@PathVariable("token") String token){
        return userLocationService.getByToken(token);
    }

    /**
     * Deleted user location with specific token
     * @param token Unique client side identifier.
     */
    @DeleteMapping(path = "{token}")
    public void deleteUserLocation(@PathVariable("token") String token){
        userLocationService.deleteUserLocation(token);
    }

    /**
     * Updates user location with specific token.
     * @param token Unique client side identifier.
     * @param req Map<String, String> Json object, hard to represent and deserialize into
     *           user location. Must contain token: String, latitude: Float, longitude: Float.
     *           Req is mapped to user location and passed to business logic.
     */
    @PutMapping(path = "{token}")
    public void updateUserLocation(
            @PathVariable("token") String token,
            @RequestBody Map<String, String> req
    ){
        // Processes request
        final String reqToken = req.get("token");
        final Float lan = Float.parseFloat(req.get("latitude"));
        final Float lon = Float.parseFloat(req.get("longitude"));
        UserLocation userLocation = new UserLocation(reqToken, lan, lon);

        logger.info(userLocation.toString());

        userLocationService.updateUserLocation(token, userLocation);
    }

    /**
     * Returns all user locations from within radius.
     * @param radius Maximal distance within points are accepted.
     * @param req Map<String, String> Json object, hard to represent and deserialize into
     *           user location. Must contain token: String, latitude: Float, longitude: Float.
     *           Req is mapped to user location and passed to business logic.
     * @return All user locations that are in smaller distance than radius.
     */
    @PostMapping(path = "/radius")
    public List<Map<String, Float>> getPointsWithinRadius(
            @RequestParam Integer radius,
            @RequestBody  Map<String, String> req
    ) {
        // Processes request
        final String token = req.get("token");
        final Float lan = Float.parseFloat(req.get("latitude"));
        final Float lon = Float.parseFloat(req.get("longitude"));

        UserLocation center = new UserLocation(token, lan, lon);

        logger.info("Get locations within radius");
        logger.info(center.toString());
        logger.info(Integer.toString(radius));

        // Maps user locations from business logic to list of shape {latitude: n, longitude: n}.
        // For client purposes.
        return userLocationService.getPointsWithinRadius(center, radius)
                .stream()
                .map(userLocation -> {
                    Map<String, Float> responseMap = new HashMap<>();
                    responseMap.put("latitude", (float) userLocation.getPoint().getX());
                    responseMap.put("longitude", (float) userLocation.getPoint().getY());
                    return responseMap;
                })
                .collect(Collectors.toList());
    }


}
