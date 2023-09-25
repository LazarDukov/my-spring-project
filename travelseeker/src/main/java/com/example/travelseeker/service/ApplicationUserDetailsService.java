package com.example.travelseeker.service;

import com.example.travelseeker.model.entities.User;
import com.example.travelseeker.model.entities.UserRole;
import com.example.travelseeker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).map(this::map).orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }

    private UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User.builder().
                username(user.getUsername()).password(user.getPassword()).authorities(
                        extractGrantedAuthority(user)).build();

    }

    private List<GrantedAuthority> extractGrantedAuthority(User user) {
        return user.getRoles().stream().map(this::mapRole).toList();
    }

    private GrantedAuthority mapRole(UserRole userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());

    }
}
