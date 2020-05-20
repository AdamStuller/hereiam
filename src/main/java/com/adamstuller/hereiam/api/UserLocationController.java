package com.adamstuller.hereiam.api;

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



}
