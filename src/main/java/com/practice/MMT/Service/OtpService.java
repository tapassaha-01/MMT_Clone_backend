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
    try {
        if (!mailOtpRepository.existsMailOtpByEmailId(email)) {
            log.info("generating otp......");
            mailOtpRepository.save(MailOtp.builder()
                    .emailId(email)
                    .otp(otp)
                    .build());
            emailService.sendOtpEmail(email, otp);
            log.info("opt has sent to the mail {}", email);
        }
        else {
            throw new RuntimeException("Email is already exist in Db, please use different one ..");
        }
    } catch (Exception e) {
        log.error(e.getMessage());
        throw new RuntimeException(e.getMessage());
    }
    return otp;
}

@Transactional
    public boolean verifyOtp(MailOtp mailOtp){
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