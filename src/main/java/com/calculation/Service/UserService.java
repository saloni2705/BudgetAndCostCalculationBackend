package com.calculation.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.calculation.Repository.UserRepository;
import com.calculation.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateCustomerDetails(Long customerId, User updatedUser) {
        User existingUser = userRepository.findById(customerId).orElse(null);

        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());

            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public  Optional<User> getUserById(Long userid) {
        return userRepository.findById(userid);
    }
}

