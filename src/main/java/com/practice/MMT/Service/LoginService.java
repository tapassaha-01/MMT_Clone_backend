package com.practice.MMT.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Repository.MailOtpRepository;
import com.practice.MMT.Repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class LoginService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authManager;
    private JWTService jwtService;
    private EmailService emailService;
    private MailOtpRepository mailOtpRepository;
    private OtpService otpService;

    @Transactional
    public UserDto registerUser(MailOtp mailOtp) {

        ModelMapper modelMapper = new ModelMapper();
        UserDto retUser = new UserDto();
        try {
            if(mailOtpRepository.existsMailOtpByOtp(mailOtp.getOtp())){
                UserEntity user = userRepository.findByEmail(mailOtp.getEmailId());
                retUser = modelMapper.map(user,UserDto.class);
                mailOtpRepository.deleteByEmailId(mailOtp.getEmailId());
                log.info("User has been registered Successfully!!");
            }
            else{
                userRepository.deleteByEmail(mailOtp.getEmailId());
                mailOtpRepository.deleteByEmailId(mailOtp.getEmailId());
                throw new RuntimeException("Otp verification failed hence not registered the user.");
            }


        } catch (Exception e) {
            log.debug("Some runtime error has been thrown : {}",e.getMessage());
            userRepository.deleteByEmail(mailOtp.getEmailId());
            return null;
        }

        return retUser;
    }


    public Map<String,String> verifyUser(String userName, String password) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        Map<String,String> retData = new HashMap<>();
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new Jdk8Module());
            if(authentication.isAuthenticated()){
                retData.put("jwtToken",jwtService.generateToken(userName));
                userRepository.findByUserName(userName);
                retData.put("user",mapper.writeValueAsString(Optional.ofNullable(userRepository.findByUserName(userName)).get()));
            }
            else{
                throw new RuntimeException("Authentication failer");
            }
        } catch (JsonProcessingException | NullPointerException e) {
            log.error("Having some error while parsing User data to String :{}",e.getMessage());
            throw new RuntimeException(e);
        }
        return retData;
    }

    @Transactional
    public String generateOtp(UserDto userDto) throws MessagingException, IOException {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        user.setPassword(passwordEncoder.encode((userDto.getPassword())));
        if(userRepository.existsUserEntityByEmail(user.getEmail())){
            return "";
        }
        userRepository.save(user);
        return otpService.generateOtp(userDto.getEmail());
    }

    public boolean logout() {
        return false;
    }
}
