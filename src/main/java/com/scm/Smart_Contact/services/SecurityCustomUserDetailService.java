package com.scm.Smart_Contact.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.Smart_Contact.repsitories.UserRepo;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found With this email"+ username));
    }

}
