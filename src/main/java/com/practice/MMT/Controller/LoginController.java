package com.practice.MMT.Controller;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Service.JWTService;
import com.practice.MMT.Service.LoginService;
import com.practice.MMT.Service.OtpService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/MMT/login")
@AllArgsConstructor
public class LoginController {

    private LoginService loginService;
    private JWTService jwtService;

    @PostMapping("register")
    public UserDto createUser(@RequestBody MailOtp mailOtp){
        return loginService.registerUser(mailOtp);
    }

    @PostMapping("generateOtp")
    public String generateOtp(@RequestBody UserDto userDto) throws MessagingException, IOException {
        return loginService.generateOtp(userDto);
    }

    @PostMapping("login")
    public ResponseEntity<Map<String,String>> login(@RequestParam String userName, @RequestParam String password){

        return ResponseEntity.ok(loginService.verifyUser(userName,password));
    }

}

