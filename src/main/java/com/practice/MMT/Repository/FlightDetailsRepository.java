package com.practice.MMT.Repository;

import com.practice.MMT.Entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails,Long> {

    List<FlightDetails> findByStartFromAndDestinationAndFlightClassAndFairType(String startFrom,String destination,String flightClass,String fairType);
}
