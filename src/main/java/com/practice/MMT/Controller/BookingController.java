package com.practice.MMT.Controller;

import com.practice.MMT.Dto.BookingDto;
import com.practice.MMT.Entity.BookingEntity;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(value = "http://localhost:4200/")
@RequestMapping("/MMT/Booking/")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping(path = "bookTicket")
    public Map<String, String> bookTicket(@RequestBody BookingDto booking){
        return bookingService.insert(booking);
    }

    @PostMapping("verifyTicketOtp")
    public boolean verifyBookingOtp(@RequestBody MailOtp mailOtp){

        return bookingService.verifyMailId(mailOtp);
    }

}
