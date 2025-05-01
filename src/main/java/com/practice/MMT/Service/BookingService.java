package com.practice.MMT.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.MMT.Dto.BookingDto;
import com.practice.MMT.Entity.BookingEntity;
import com.practice.MMT.Entity.PassengerEntity;
import com.practice.MMT.Repository.BookingRepository;
import com.practice.MMT.Repository.PassengerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private PassengerRepository passengerRepository;

    @Transactional
    public BookingEntity insert(BookingDto booking){

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {

            BookingEntity bookingEntity = om.convertValue(booking, BookingEntity.class);
            Set<PassengerEntity> passengerList = bookingEntity.getPassengers();
            for (PassengerEntity passenger : passengerList) {
                passenger.getBookings().add(bookingEntity);
            }
            bookingEntity.setPassengers(passengerList);
            passengerRepository.saveAll(passengerList);
            log.info("All the records has been saved successfully!!");
            return bookingRepository.save(bookingEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
