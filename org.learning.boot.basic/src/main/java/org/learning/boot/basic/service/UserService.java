package org.learning.boot.basic.service;

import java.util.List;
import java.util.Optional;

import org.learning.boot.basic.jpa.Users;
import org.learning.boot.basic.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Marks this class as a Spring Service component - Spring will create and manage this bean
 * Service acts as a leyer between database repo and controller
 */
@Service
public class UserService {
    
    @Autowired // Tells Spring to inject the UserRepository instance into this field
    private UserRepository userRepository;
    
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Users createUser(Users user) {
        return userRepository.save(user);
    }
    
    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public Users updateUser(Long id, Users userDetails) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}