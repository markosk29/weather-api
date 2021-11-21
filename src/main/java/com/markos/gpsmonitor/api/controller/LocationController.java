package com.markos.gpsmonitor.api.controller;

import com.markos.gpsmonitor.api.model.LocationInput;
import com.markos.gpsmonitor.api.output.LocationJSON;
import com.markos.gpsmonitor.api.service.LocationService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("location")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationJSON> saveLocation(@NotNull LocationInput locationInput) {
        return new ResponseEntity<>(locationService.saveLocation(locationInput), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getLocationInInterval(@RequestParam String startDateAndTime, @RequestParam String endDateAndTime) {
        return new ResponseEntity<>(locationService.getLocationInInterval(startDateAndTime, endDateAndTime), HttpStatus.OK);
    }
}
