package com.practice.MMT.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDto {
    private String userName;
    private String email;
    private String oldEmail;
    private Long phoneNo;
}
