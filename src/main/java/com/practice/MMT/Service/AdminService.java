package com.practice.MMT.Service;

import com.practice.MMT.Dto.FlightDetailsDto;
import com.practice.MMT.Entity.FlightDetails;
import com.practice.MMT.Repository.FlightDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AdminService {

    private FlightDetailsRepository flightDetailsRepo;

    public FlightDetails setFlightDetails(MultipartFile file) {
        List<FlightDetails> flightDetailsDtoList = new ArrayList<>();
//        String filePath = "";
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Skip header
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                FlightDetails flightDetails = new FlightDetails();
                flightDetails.setPlaneCompanyName(getStringValue(row.getCell(0)));
                if(row.getCell(1)==null|| row.getCell(2)==null) {
                	continue;
                }
                flightDetails.setDepartureDate(getDateValue(row.getCell(1)));
                flightDetails.setArrivalDate(getDateValue(row.getCell(2)));
                flightDetails.setStartFrom(getStringValue(row.getCell(3)));
                flightDetails.setDestination(getStringValue(row.getCell(4)));
//                flightDetailsDto.setTravelTime(getDateValue(row.getCell(5)));
                flightDetails.setPrice(getNumericValue(row.getCell(6)));
                flightDetails.setFlightClass(getStringValue(row.getCell(7)));
                flightDetails.setFairType(getStringValue(row.getCell(8)));
                flightDetailsDtoList.add(flightDetails);
            }
            flightDetailsRepo.saveAll(flightDetailsDtoList);
            System.out.println(flightDetailsDtoList);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private String getStringValue(Cell cell) {
        return cell != null ? cell.toString().trim() : "";
    }

    private double getNumericValue(Cell cell) {
        if (cell == null) return 0.0;
        if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
        try {
            return Double.parseDouble(cell.toString().trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    private LocalDate getDateValue(Cell cell){
//    	String blank_value = "0000-00-00 00:00";
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//    	if(cell==null) {
//    		return LocalDate.parse(LocalDate.now().toString(),formatter);
//    	}
//        if (cell.getCellType() == CellType.NUMERIC || DateUtil.isCellDateFormatted(cell)) {
        
        return LocalDate.parse(cell.getStringCellValue(), formatter);

//        return LocalDate.now();
    }

    public List<FlightDetails> getFlightDetails(FlightDetailsDto flightDetailsDto) {
        log.info(flightDetailsDto.toString());
        return flightDetailsRepo.findByStartFromAndDestinationAndFlightClassAndFairType(
                flightDetailsDto.getStartFrom(),flightDetailsDto.getDestination()
                        ,flightDetailsDto.getFlightClass(),flightDetailsDto.getFairType());
    }
}
