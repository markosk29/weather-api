package com.markos.gpsmonitor.api.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationInput {

    private double latitude;
    private double longitude;
    private String device;
}
