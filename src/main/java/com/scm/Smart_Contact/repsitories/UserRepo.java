package com.scm.Smart_Contact.repsitories;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.scm.Smart_Contact.entities.User;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
}
