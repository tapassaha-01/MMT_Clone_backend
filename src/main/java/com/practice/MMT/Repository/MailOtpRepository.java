package com.practice.MMT.Repository;


import com.practice.MMT.Entity.MailOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailOtpRepository extends JpaRepository<MailOtp,Long> {
    boolean existsMailOtpByOtp(String otp);
    boolean existsMailOtpByEmailId(String email);
    void deleteByEmailId(String email);
}
