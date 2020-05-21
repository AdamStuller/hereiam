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
import java.util.stream.Stream;

@RequestMapping("api/v1/userLocation")
@RestController
public class UserLocationController {

    private final UserLocationService userLocationService;
    Logger logger = LoggerFactory.getLogger(UserLocationController.class);

    @Autowired
    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserLocation(@Validated @RequestBody Map<String, String> req){
        final String token = req.get("token");
        final Float lan = Float.parseFloat(req.get("latitude"));
        final Float lon = Float.parseFloat(req.get("longitude"));
        UserLocation userLocation= new UserLocation(token, lan, lon);
        logger.info("Add user location accepted");
        logger.info( userLocation.toString());
        userLocationService.addUserLocation(userLocation);
    }

    @GetMapping
    public List<UserLocation> getAllUserLocations(){
        logger.info("All user locations accepted!");
        return userLocationService.getAllUserLocation();
    }

    @GetMapping( path = "{token}")
    public UserLocation getByToken(@PathVariable("token") String token){
        return userLocationService.getByToken(token);
    }

    @DeleteMapping(path = "{token}")
    public void deleteUserLocation(@PathVariable("token") String token){
        userLocationService.deleteUserLocation(token);
    }

    @PutMapping(path = "{token}")
    public int updateUserLocation(
            @PathVariable("token") String token,
            @RequestBody Map<String, String> req
    ){
        final String reqToken = req.get("token");
        final Float lan = Float.parseFloat(req.get("latitude"));
        final Float lon = Float.parseFloat(req.get("longitude"));
        UserLocation userLocation = new UserLocation(reqToken, lan, lon);
        logger.info(userLocation.toString());
        return userLocationService.updateUserLocation(token, userLocation);
    }

    @PostMapping(path = "/radius")
    public Stream<Map<String, Float>> getPointsWithinRadius(
            @RequestParam Integer radius,
            @RequestBody  Map<String, String> req
    ) {
        final String token = req.get("token");
        final Float lan = Float.parseFloat(req.get("latitude"));
        final Float lon = Float.parseFloat(req.get("longitude"));
        UserLocation center = new UserLocation(token, lan, lon);
        logger.info("Get locations within radius");
        logger.info(center.toString());
        logger.info(Integer.toString(radius));


        return userLocationService.getPointsWithinRadius(center, radius)
                .stream()
                .map(userLocation -> {
                    Map<String, Float> responseMap = new HashMap<>();
                    responseMap.put("latitude", (float) userLocation.getPoint().getX());
                    responseMap.put("longitude", (float) userLocation.getPoint().getY());
                    return responseMap;
                });
    }


}
