package com.Project.MentorConnect.Security;

import com.Project.MentorConnect.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustemUserDetailsService implements UserDetailsService {

    UserRepo userRepo;

    @Autowired
    public CustemUserDetailsService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email).orElseThrow();
    }
}
