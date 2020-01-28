package com.sports.booking.controllers;

import com.sports.booking.database.models.Sport;
import com.sports.booking.dto.LocationDto;
import com.sports.booking.dto.SportDto;
import com.sports.booking.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {


    public static final String BASE_URL = "api/v1/user";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/findLocation", method = RequestMethod.GET)
    public List<LocationDto> findBestPeriodForSport(@RequestBody SportDto sport) {
        return userService.getLocationsByCost(sport);
    }
    @RequestMapping(value = "/findSport", method = RequestMethod.GET)
    public Sport findSport(@RequestBody SportDto sport) {
        return userService.getSportByName(sport);
    }
}