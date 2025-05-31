package com.practice.MMT.Controller;

import com.practice.MMT.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class JwtController {

    @Autowired
    private JWTService jwtService;

    @GetMapping("/generateToken")
    public String jwtTokenGenerator(@RequestParam String username){
        return jwtService.generateToken(username);
    }
}
