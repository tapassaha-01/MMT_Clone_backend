package com.practice.MMT.Repository;

import com.practice.MMT.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
@Query(value = "SELECT TRIM(TO_CHAR(booking_time, 'Month')) AS month, SUM(cost) AS totalCost FROM booking_entity GROUP BY TRIM(TO_CHAR(booking_time, 'Month')), EXTRACT(MONTH FROM booking_time) \n" +
        "ORDER BY EXTRACT(MONTH FROM booking_time)"
,nativeQuery = true)
    List<Object[]> getMonthlyRevenue();
}
