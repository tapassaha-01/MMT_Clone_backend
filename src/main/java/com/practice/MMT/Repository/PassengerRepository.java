package com.practice.MMT.Repository;

import com.practice.MMT.Entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<PassengerEntity,Long> {
}
