package com.practice.MMT.Repository;

import com.practice.MMT.Dto.UserDto;
import com.practice.MMT.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity,Long> {
}
