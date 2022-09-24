package com.nnk.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.web.model.User;

/**
 * This interface allows to communicate with the DB only for the User Entity
 * 
 * @author Rémi Deronzier
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}
