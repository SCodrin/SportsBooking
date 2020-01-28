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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final LocationRepository locationRepository;

    private final SportRepository sportRepository;

    private final CostRepository costRepository;

    @Autowired
    public UserService(LocationRepository locationRepository, SportRepository sportRepository, CostRepository costRepository) {
        this.locationRepository = locationRepository;
        this.sportRepository = sportRepository;
        this.costRepository = costRepository;
    }

    public List<LocationDto> getLocationsByCost(SportDto sportDto) {
        List<LocationDto> locations = new ArrayList<LocationDto>();
        List<Sport> sports = sportRepository.findSportBetweenDates(sportDto.getStart_date(), sportDto.getEnd_date());
        for (Sport sport : sports) {
            List<SportDto> sportDtos = new ArrayList<SportDto>();
            Optional<Cost> cost = costRepository.getCostBySport_id(sport.getSport_id());
            Optional<Location> location = locationRepository.findById(cost.get().getLocation_id());
            SportDto sportDto1 = SportDto.builder().cost(cost.get().getAmount()).seatsAvailable(cost.get().getSpots()).build();

            sportDtos.add(sportDto1);
            LocationDto locationDto = LocationDto.builder().city(location.get().getCity())
                    .country(location.get().getCountry()).region(location.get().getRegion())
                    .sports(sportDtos)
                    .build();
            locations.add(locationDto);
        }
        return locations;
    }

    public Sport getSportByName(SportDto sp) {

        return sportRepository.findSportsByName(sp.getName());
    }
}
