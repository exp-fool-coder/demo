package com.example.demo.manager;

import com.example.demo.entity.User;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserManager {

    @Autowired
    private UserRepository userRepository;

    public User findByLogin(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.orElse(null);
    }

    public User create(String login, String encodedPassword) {
        User user = new User(
                login,
                encodedPassword
        );
        return userRepository.save(user);
    }
}
