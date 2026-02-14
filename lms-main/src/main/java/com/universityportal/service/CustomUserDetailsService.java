package com.universityportal.service;

import com.universityportal.entity.User;
import com.universityportal.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Create UserDetails object
        // Use user.getId() or similar as unique identifier if needed, but Spring
        // Security uses username typically.
        // We will store ID in our custom UserDetails implementation if needed, or just
        // use standard User.

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmailAddress())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) // Add roles later (ROLE_STUDENT, ROLE_TEACHER)
                .build();
    }
}
