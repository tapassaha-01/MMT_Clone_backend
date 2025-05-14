package com.practice.MMT.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FlightDetailsDto {

    private String startFrom;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String flightClass;
    private String fairType;


}
