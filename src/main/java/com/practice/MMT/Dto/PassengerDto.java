package com.practice.MMT.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class PassengerDto {
    private String name;
    private Long phoneNo;
    private Long addharNo;
    private int age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String berthType;
    private String transportType;
    private String gender;
    private String nationality;
    private Set<BookingDto> bookings=new HashSet<>();
}
