package com.practice.MMT.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id=null;
    private String userName;
    private String email;
    private Long phoneNo;
    private String password;
    private boolean isAdmin;
}
