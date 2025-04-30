package com.practice.MMT.Repository;

import com.practice.MMT.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
     UserEntity findByEmailAndPassword(String emailId, String pass);
    UserEntity findByEmail(String email);
    void deleteByEmail(String emailId);


   boolean existsUserEntityByEmail(String email);

    Optional<UserEntity> findByUserName(String username);
}
