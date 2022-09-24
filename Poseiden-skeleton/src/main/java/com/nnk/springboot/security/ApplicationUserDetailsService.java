package com.nnk.springboot.security;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repository.UserRepository;
import com.nnk.springboot.web.model.User;

/**
 * This class allows to configure the UserDetailsService when a user is logging
 * (get appropriate authorities + useful information in the DB)
 * 
 * @author Rémi Deronzier
 */
@Service
@Transactional
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                true,
                true,
                true, true, getAuthorities(user.getRole()));
    }

    /**
     * @param role
     * @return Collection<? extends GrantedAuthority>
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

}
