package com.practice.MMT.Controller;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MMT/")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("register")
    public UserDto createuser(@RequestBody UserDto userDto){
        return loginService.registerUser(userDto);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
             return ResponseEntity.ok(loginService.verifyUser(userDto));
    }
//    @GetMapping("/logout")
//    public boolean logout(){
//        return loginService.logout();
//    }

    }

