package com.practice.MMT.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endingDate;
    private String startFrom;
    private String endTo;
    private String bookingClass;
    private Long passengerNo;


    @ManyToMany(mappedBy = "bookings")
    private Set<PassengerEntity> passengers=new HashSet<>() ;

    public Set<PassengerEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<PassengerEntity> passengers) {
        this.passengers = passengers;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public String getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(String startFrom) {
        this.startFrom = startFrom;
    }

    public String getEndTo() {
        return endTo;
    }

    public void setEndTo(String endTo) {
        this.endTo = endTo;
    }

    public String getBookingClass() {
        return bookingClass;
    }

    public void setBookingClass(String bookingClass) {
        this.bookingClass = bookingClass;
    }

    public Long getPassengerNo() {
        return passengerNo;
    }

    public void setPassengerNo(Long passengerNo) {
        this.passengerNo = passengerNo;
    }
}
