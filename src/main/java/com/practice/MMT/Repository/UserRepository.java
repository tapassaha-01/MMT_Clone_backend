package com.practice.MMT.Repository;

import com.practice.MMT.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmailAndPassword(String emailId, String pass);
    public UserEntity findByEmail(String email);


//    boolean existsByEmail(String email);

    Optional<UserEntity> findByUserName(String username);
}
