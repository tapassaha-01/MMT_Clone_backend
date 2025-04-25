package com.practice.MMT.Service;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Repository.MailOtpRepository;
import com.practice.MMT.Repository.UserRepository;
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

    @Transactional
    public UserDto registerUser(MailOtp mailOtp) {

        ModelMapper modelMapper = new ModelMapper();
        UserDto retUser = new UserDto();
        try {
            if(mailOtpRepository.existsMailOtpByEmailId(mailOtp.getEmailId())){
                UserEntity user = userRepository.findByEmail(mailOtp.getEmailId());
                retUser = modelMapper.map(user,UserDto.class);
                mailOtpRepository.deleteByEmailId(mailOtp.getEmailId());
                log.info("User has been registered Successfully!!");
            }
            else{
                userRepository.deleteById(retUser.getId());
                mailOtpRepository.deleteById(mailOtp.getId());
                throw new RuntimeException("Otp verification failed hence not registered the user.");
            }


        } catch (Exception e) {
            log.debug("Some runtime error has been thrown : {}",e.getMessage());
        }

        return retUser;
    }


    public String verifyUser(UserDto userDto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(),userDto.getPassword()));
       return authentication.isAuthenticated()?jwtService.generateToken(userDto.getUserName()):"Fail";
    }


    public boolean logout() {
        return false;
    }
}
