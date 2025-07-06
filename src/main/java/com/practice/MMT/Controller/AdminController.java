package com.practice.MMT.Controller;

import com.practice.MMT.Dto.CitiesList;
import com.practice.MMT.Dto.FlightDetailsDto;
import com.practice.MMT.Entity.FlightDetails;
import com.practice.MMT.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/MMT/Admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("getFlightDetails")
    private ResponseEntity<List<FlightDetails>> getFlightDetsails(@RequestBody FlightDetailsDto flightDetailsDto) {

        return ResponseEntity.ok(adminService.getFlightDetails(flightDetailsDto));
    }

    @PostMapping("setDetails")
    private boolean setFlightDetails(@RequestParam("file") MultipartFile file){
        adminService.setFlightDetails(file);
        return true;
    }
    @GetMapping("/getCitiesDetails")
    public Map<String,List<String>> getCities(){
        return adminService.getCities();
    }
}
