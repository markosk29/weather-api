package com.markos.gpsmonitor.api.mapper;

import com.markos.gpsmonitor.api.entity.Location;
import com.markos.gpsmonitor.api.model.LocationInput;
import com.markos.gpsmonitor.api.output.LocationJSON;

import java.time.LocalDateTime;

public class LocationMapper {

    public static Location inputToEntity(LocationInput locationInput) {
        return Location.builder()
                .latitude(locationInput.getLatitude())
                .longitude(locationInput.getLongitude())
                .device(locationInput.getDevice())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static LocationJSON entityToJSON(Location location) {
        return LocationJSON.builder()
                .id(location.getId())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .device(location.getDevice())
                .creationDate(location.getCreationDate().toString())
                .build();
    }
}
