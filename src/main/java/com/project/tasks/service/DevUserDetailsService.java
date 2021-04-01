package com.project.tasks.service;

import com.project.tasks.repository.DevUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevUserDetailsService implements UserDetailsService {

    private final DevUserRepository devUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(devUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Dev User not found!"));
    }
}
