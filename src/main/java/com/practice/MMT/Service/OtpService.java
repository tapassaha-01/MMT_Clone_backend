package com.practice.MMT.Service;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Repository.MailOtpRepository;
import com.practice.MMT.Repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class OtpService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MailOtpRepository mailOtpRepository;
    private EmailService emailService;

    @Transactional
    public String generateOtp(UserDto userDto) throws MessagingException, IOException {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode((userDto.getPassword())));
        userRepository.save(user);
        String otp = String.valueOf(new Random().nextInt(999999));
        mailOtpRepository.save(MailOtp.builder()
                .emailId(user.getEmail())
                .otp(otp)
                .build());
        emailService.sendOtpEmail(userDto.getEmail(),otp);
        return otp;

    }




}