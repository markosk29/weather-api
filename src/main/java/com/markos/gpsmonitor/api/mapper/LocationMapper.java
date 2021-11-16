package com.markos.gpsmonitor.api.mapper;

import com.markos.gpsmonitor.api.entity.Location;
import com.markos.gpsmonitor.api.model.LocationInput;

import java.time.LocalDateTime;

public class LocationMapper {

    private static Location inputToEntity(LocationInput locationInput) {
        return Location.builder()
                .latitude(locationInput.getLatitude())
                .longitude(locationInput.getLongitude())
                .device(locationInput.getDevice())
                .creationDate(LocalDateTime.now())
                .build();
    }
}
