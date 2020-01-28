package com.sports.booking.controllers;

import com.sports.booking.database.models.Location;
import com.sports.booking.dto.LocationDto;
import com.sports.booking.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AdminController.BASE_URL)
public class AdminController {

    public static final String BASE_URL = "api/v1/admin";
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/getLocations", method = RequestMethod.GET)
    public List<Location> getLocations() {
        return adminService.getAllLocations();
    }

    @RequestMapping(value = "/getLocationById/{locationId}", method = RequestMethod.GET)
    public Location getLocationById(@PathVariable(name = "locationId") Long locationId) {
        if (Optional.ofNullable(adminService.getLocationById(locationId)).isPresent()) {
            return adminService.getLocationById(locationId).get();
        }
        return null;
    }

    @RequestMapping(value = "/createLocation", method = RequestMethod.POST)
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        try {
            return adminService.createLocation(locationDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/updateLocation/{locationId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateLocation(@RequestBody LocationDto locationDto,
                                                 @PathVariable(name = "locationId") Long locationId) {
        try {
            return adminService.updateLocation(locationDto, locationId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/deleteLocation/{locationId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLocation(@PathVariable(name = "locationId") Long locationId) {
        try {
            return adminService.deleteLocation(locationId);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
