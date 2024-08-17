package com.scm.Smart_Contact.services;

import java.util.List;  
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.Smart_Contact.entities.User;
import com.scm.Smart_Contact.helpers.AppConstants;
import com.scm.Smart_Contact.helpers.ResourceNotFoundException;
import com.scm.Smart_Contact.repsitories.UserRepo;

@Service
public class UserServiceImpl implements UserService { 

    @Autowired
    private UserRepo userRepo; 
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();	
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setEnabled(user.isEnabled());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        user2.setProfilePic(user.getProfilePic());
        
        User savedUser = userRepo.save(user2);
        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}
