package com.practice.MMT.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.MMT.Dto.BookingDto;
import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.BookingEntity;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Entity.PassengerEntity;
import com.practice.MMT.Repository.BookingRepository;
import com.practice.MMT.Repository.MailOtpRepository;
import com.practice.MMT.Repository.PassengerRepository;
import io.swagger.v3.core.util.Json;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private PassengerRepository passengerRepository;
    private OtpService otpService;
    private MailOtpRepository mailOtpRepository;

    @Transactional
    public Map<String, String> insert(BookingDto booking){
        Map<String, String> retBooking = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {

            BookingEntity bookingEntity = om.convertValue(booking,BookingEntity.class);
            Set<PassengerEntity> passengerList = bookingEntity.getPassengers();
            for (PassengerEntity passenger : passengerList) {
                passenger.getBookings().add(bookingEntity);
            }
            bookingEntity.setPassengers(passengerList);
            passengerRepository.saveAll(passengerList);
            log.info("All the records has been saved successfully!!");
            bookingRepository.save(bookingEntity);
            retBooking.put("bookingDetails", om.writeValueAsString(BookingDto.builder()
                    .startDate(booking.getStartDate())
                    .endingDate(booking.getEndingDate())
                    .startFrom(booking.getStartFrom())
                    .endTo(booking.getEndTo())
                    .bookingClass(booking.getBookingClass())
                    .passengerNo(booking.getPassengerNo())
                            .emailId(booking.getEmailId())
                    .passengers(booking.getPassengers())
                    .cost(booking.getCost())
                    .bookingTime(booking.getBookingTime()).build()));
            return retBooking;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean verifyMailId(MailOtp mailOtp) {
        boolean verify = false;
        try{
        if(mailOtpRepository.existsMailOtpByOtp(mailOtp.getOtp())){
            mailOtpRepository.deleteByEmailId(mailOtp.getEmailId());
            verify = true;

        }}catch (Exception e){
            mailOtpRepository.deleteByEmailId(mailOtp.getEmailId());
            throw new RuntimeException("Entered Otp is not valid");

        }
        return verify;
    }
}
