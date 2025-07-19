package com.practice.MMT.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashBoardDto {
    private Long totalBooking;
    private Long totalUser;
    Map<String,String> revenue;
}
