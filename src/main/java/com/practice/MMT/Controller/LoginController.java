package com.practice.MMT.Controller;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Service.LoginService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/MMT/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/create")
    public UserDto createuser(@RequestBody UserDto userDto){
        return loginService.registerUser(userDto);
    }
}
