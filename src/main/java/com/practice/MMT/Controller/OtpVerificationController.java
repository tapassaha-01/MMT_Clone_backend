package com.practice.MMT.Controller;

import com.practice.MMT.Service.EmailService;
import com.practice.MMT.Service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/otp/")
public class OtpVerificationController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    // Endpoint to generate and send OTP
//    @PostMapping("/send")
//    public ResponseEntity<String> sendOtp(@RequestParam String email) {
//        String otp = otpService.generateOtp(email);
//        emailService.sendOtpEmail(email, otp);
//        return ResponseEntity.ok(otp);
//    }
//
//    // Endpoint to verify OTP
//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
//        if (otpService.validateOtp(email, otp)) {
//            otpService.clearOtp(email);
//            return ResponseEntity.ok("OTP verified successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
//        }
//    }

}
