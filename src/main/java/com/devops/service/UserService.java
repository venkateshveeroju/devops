package com.devops.service;

import com.devops.repo.UserRepository;
import com.devops.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // Method to register the user
    public boolean registerUser(User user) {
        // Check if the user already exists
              // Encode the password before saving to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the database
        User userSaved = userRepository.save(user);
        if(userSaved.getUsername()!=null) {
            return true;
        }
        return false;
    }

    // Method to check user credentials during login
    public User authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;  // Authentication successful
        }
        return null;  // Authentication failed
    }
}