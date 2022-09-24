package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.UserRepository;
import com.nnk.springboot.web.model.User;

/**
 * This class contains the business logic for the User Entity
 * 
 * @author Rémi Deronzier
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @return List<User>
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @param id
     * @return User
     */
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
    }

    /**
     * @param user
     * @return User
     */
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        Optional<User> optionalCurvePoint = userRepository.findById(id);
        if (optionalCurvePoint.isPresent()) {
            userRepository.deleteById(id);
        } else {
            new IllegalArgumentException("Invalid User Id:" + id);
        }
    }

}
