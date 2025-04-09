package com.practice.MMT.Repository;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmailAndPassword(String emailId, String pass);
}
