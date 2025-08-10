package com.practice.MMT.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @Column(unique = true,nullable = false)
    private String email;
    private Long phoneNo;

    @Column(unique = true,nullable = false)
    private String password;
    private boolean isAdmin;



}
