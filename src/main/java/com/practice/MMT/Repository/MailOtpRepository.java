package com.practice.MMT.Repository;


import com.practice.MMT.Entity.MailOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailOtpRepository extends JpaRepository<MailOtp,Long> {
//    private boolean ex
    public boolean existsMailOtpByEmailId(String emailId);
    MailOtp deleteByEmailId(String email);
}
