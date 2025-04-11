package com.practice.MMT.Service;

import com.practice.MMT.Entity.UserPrinciple;
import com.practice.MMT.Entity.UserEntity;
import com.practice.MMT.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MMTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("Email id is not present in the DB") );

        return new UserPrinciple(user);
    }
}
