package com.markos.gpsmonitor.api.output;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationJSON {

    private int id;
    private double latitude;
    private double longitude;
    private String device;
    private String creationDate;
}
