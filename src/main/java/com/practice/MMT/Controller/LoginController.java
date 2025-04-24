package com.practice.MMT.Controller;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.MailOtp;
import com.practice.MMT.Service.LoginService;
import com.practice.MMT.Service.OtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/MMT/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private OtpService otpService;

    @PostMapping("register")
    public UserDto createUser(@RequestBody MailOtp mailOtp){
        return loginService.registerUser(mailOtp);
    }

    @PostMapping("generateOtp")
    public String verifyOtp(@RequestBody UserDto userDto){
        return otpService.generateOtp(userDto);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
             return ResponseEntity.ok(loginService.verifyUser(userDto));
    }

    @GetMapping("test")
    public String test(@RequestParam String name){
        return name;
    }

//    @GetMapping("/logout")
//    public boolean logout(){
//        return loginService.logout();
//    }

    }

