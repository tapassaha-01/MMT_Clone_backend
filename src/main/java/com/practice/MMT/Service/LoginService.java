package com.practice.MMT.Service;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Exception.UserNotFoundException;
import com.practice.MMT.Repository.LoginRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

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

    public UserDto verifyUser(String email, String password) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<UserEntity> user = Optional.ofNullable(loginRepository.findByEmailAndPassword(email, password));
       UserDto retUser =  modelMapper.map(user, UserDto.class);
        if(user.isEmpty()){
            throw new UserNotFoundException("user not present in the Database, Please Register yourself!!");
        }
        return retUser;
    }


}
