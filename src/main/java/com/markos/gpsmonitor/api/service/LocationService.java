package com.markos.gpsmonitor.api.service;

import com.markos.gpsmonitor.api.entity.Location;
import com.markos.gpsmonitor.api.mapper.LocationMapper;
import com.markos.gpsmonitor.api.model.LocationInput;
import com.markos.gpsmonitor.api.output.LocationJSON;
import com.markos.gpsmonitor.api.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationJSON saveLocation(LocationInput locationInput) {
        Location location = LocationMapper.inputToEntity(locationInput);

        locationRepository.save(location);

        return LocationMapper.entityToJSON(location);
    }

    public List<LocationJSON> getLocationInInterval(String startDateAndTimeString, String endDateAndTimeString) {
        try {
            LocalDateTime.parse(startDateAndTimeString);
            LocalDateTime.parse(endDateAndTimeString);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date input. Must be of format '2021-12-29T21:05:11'");
        }

        LocalDateTime startDateAndTime = LocalDateTime.parse(startDateAndTimeString);
        LocalDateTime endDateAndTime = LocalDateTime.parse(endDateAndTimeString);

        List<Location> locations = locationRepository.findAll(where(isInTimeInterval(startDateAndTime, endDateAndTime)));

        if (locations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No locations found for the given time interval!");
        }

        return locations.stream()
                .map(LocationMapper::entityToJSON)
                .collect(Collectors.toList());
    }

    // JPA Specification conditional filter
    private Specification<Location> isInTimeInterval(LocalDateTime startDateAndTime, LocalDateTime endDateAndTime) {
        return (locationRoot, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.between(locationRoot.get("creationDate"), startDateAndTime, endDateAndTime);
    }

}
