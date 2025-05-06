package com.practice.MMT.Service;

import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Repository.MailOtpRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class OtpService {


    private MailOtpRepository mailOtpRepository;
    private EmailService emailService;


@Transactional
public String generateOtp(String email) throws MessagingException, IOException {
    String otp = String.format("%06d", new Random().nextInt(999999));
    mailOtpRepository.save(MailOtp.builder()
            .emailId(email)
            .otp(otp)
            .build());
    emailService.sendOtpEmail(email,otp);
    return otp;
}


}