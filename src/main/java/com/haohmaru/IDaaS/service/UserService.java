package com.haohmaru.IDaaS.service;

import com.haohmaru.IDaaS.dto.request.UserCreationRequest;
import com.haohmaru.IDaaS.dto.request.UserUpdateRequest;
import com.haohmaru.IDaaS.entity.User;
import com.haohmaru.IDaaS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        // Map User info from Request into User model
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Cannot create this User, it's existed !");

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user); // Create a new row in table
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        // If there are huge number of services -> cost efforts to handle exception like this below.
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
