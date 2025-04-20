package com.practice.MMT.Service;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Exception.UserNotFoundException;
import com.practice.MMT.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authManager;
    private JWTService jwtService;

    public UserDto registerUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            UserEntity user = modelMapper.map(userDto, UserEntity.class);
            user.setPassword(passwordEncoder.encode((userDto.getPassword())));
            user = userRepository.save(user);
            userDto.setId(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return userDto;
    }

    public String verifyUser(UserDto userDto) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(),userDto.getPassword()));
       return authentication.isAuthenticated()?jwtService.generateToken(userDto.getUserName()):"Fail";
    }


    public boolean logout() {
        return false;
    }
}
