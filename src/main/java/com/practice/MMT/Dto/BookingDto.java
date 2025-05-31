package com.practice.MMT.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.MMT.Entity.PassengerEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class BookingDto {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endingDate;
    private String startFrom;
    private String endTo;
    private String bookingClass;
    private Long passengerNo;
    private String emailId;
    private Set<PassengerDto> passengers=new HashSet<>() ;
    private double cost;
    private LocalDate bookingTime;


}
