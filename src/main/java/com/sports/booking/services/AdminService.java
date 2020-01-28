package com.sports.booking.services;

import com.sports.booking.database.models.Cost;
import com.sports.booking.database.models.Location;
import com.sports.booking.database.models.Sport;
import com.sports.booking.database.repositories.CostRepository;
import com.sports.booking.database.repositories.LocationRepository;
import com.sports.booking.database.repositories.SportRepository;
import com.sports.booking.dto.LocationDto;
import com.sports.booking.dto.SportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final LocationRepository locationRepository;

    private final SportRepository sportRepository;

    private final CostRepository costRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");


    @Autowired
    public AdminService(LocationRepository locationRepository, SportRepository sportRepository, CostRepository costRepository) {
        this.locationRepository = locationRepository;
        this.sportRepository = sportRepository;
        this.costRepository = costRepository;
    }


    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations;
    }


    public ResponseEntity<LocationDto> createLocation(LocationDto locationDto) {

        Location location = Location.builder().city(locationDto.getCity())
                .country(locationDto.getCountry())
                .region(locationDto.getRegion())
                .build();
        Location savedLocation = locationRepository.save(location);
        List<SportDto> sports = locationDto.getSports();
        for (SportDto sport : sports) {

            Sport spor = Sport.builder().name(sport.getName())
                    .start_date(new Date(sdf.format(sport.getStart_date())))
                    .end_date(new Date(sdf.format(sport.getEnd_date())))
                    .build();
            Sport savedSport = sportRepository.save(spor);

            Cost cost = Cost.builder().amount(sport.getCost())
                    .spots(sport.getSeatsAvailable())
                    .location_id(savedLocation.getLocation_id())
                    .sport_id(savedSport.getSport_id())
                    .build();
            costRepository.save(cost);
        }

        return null;
    }

    public ResponseEntity<String> updateLocation(LocationDto locationDto, Long locationId) {
        Optional<Location> locationFound = locationRepository.findById(locationId);
        if (Optional.ofNullable(locationFound).isPresent()) {
            Location location = Location.builder().city(locationDto.getCity())
                    .country(locationDto.getCountry())
                    .region(locationDto.getRegion())
                    .location_id(locationFound.get().getLocation_id())
                    .build();
            locationRepository.save(location);
            List<SportDto> sports = locationDto.getSports();
            for (SportDto sport : sports) {
                Sport spor = Sport.builder().name(sport.getName())
                        .start_date(new Date(sdf.format(sport.getStart_date())))
                        .end_date(new Date(sdf.format(sport.getEnd_date())))
                        .build();
                Sport savedSport = sportRepository.save(spor);

                Cost cost = Cost.builder().amount(sport.getCost())
                        .spots(sport.getSeatsAvailable())
                        .location_id(locationId)
                        .sport_id(savedSport.getSport_id())
                        .build();
                costRepository.save(cost);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Optional<Location> getLocationById(Long locationId) {
        return locationRepository.findById(locationId);
    }

    public ResponseEntity<String> deleteLocation(Long locationId) {
        try {
            locationRepository.deleteById(locationId);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
