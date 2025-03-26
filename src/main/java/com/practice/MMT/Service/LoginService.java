package com.practice.MMT.Service;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Repository.LoginRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {


    private LoginRepository loginRepository;

    public UserDto registerUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
try{
    UserEntity user = modelMapper.map(userDto, UserEntity.class);

    user = loginRepository.save(user);
    userDto.setId(user.getId());
}catch (Exception e){
    e.printStackTrace();
}


        return userDto;
    }
}
