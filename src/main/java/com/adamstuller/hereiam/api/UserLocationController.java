package com.adamstuller.hereiam.api;

import com.adamstuller.hereiam.models.Point;
import com.adamstuller.hereiam.models.UserLocation;
import com.adamstuller.hereiam.service.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/userLocation")
@RestController
public class UserLocationController {

    private final UserLocationService userLocationService;

    @Autowired
    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @PostMapping
    public void addUserLocation(@RequestBody UserLocation userLocation){
        userLocationService.addUserLocation(userLocation);
    }

    @GetMapping
    public List<UserLocation> getAllUserLocations(){
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
            @RequestBody UserLocation userLocation
    ){
        return userLocationService.updateUserLocation(token, userLocation);
    }

    @PostMapping(path = "/radius")
    public List<Point> getPointsWithinRadius(
            @RequestParam Integer radius,
            @RequestBody UserLocation center
    ) {
        return userLocationService.getPointsWithinRadius(center, radius);
    }


}
