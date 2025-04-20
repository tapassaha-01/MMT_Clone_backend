package com.practice.MMT.Entity;

import jakarta.persistence.*;
import lombok.Data;

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
