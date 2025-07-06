package com.practice.MMT.Controller;

import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Service.EmailService;
import com.practice.MMT.Service.OtpService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/otp/")
public class OtpVerificationController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    // Endpoint to generate and send OTP
    @PostMapping("otpGenerate")
    public ResponseEntity<String> sendOtp(@RequestParam String emailId) throws MessagingException, IOException {
        try{
            String otp = otpService.generateOtp(emailId);
            return ResponseEntity.ok(otp);
        }catch (MessagingException | IOException | RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    String.format("{\"error\":\"%s\",\"message\":\"%s\"}",e.getClass().getSimpleName(),e.getMessage())
            );
        }

    }

    // Endpoint to verify OTP
    @PostMapping("otpVerify")
    public ResponseEntity<Map<String, String>> verifyOtp(@RequestBody MailOtp mailOtp) {
        if (otpService.verifyOtp(mailOtp)) {
            return ResponseEntity.ok(Collections.singletonMap("msg", "OTP verified successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("msg", "Invalid OTP."));
        }
    }

}
