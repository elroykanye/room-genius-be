package com.sharonghranui.roomgenius.business.service;

import com.sharonghranui.roomgenius.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RgUserDetailsService implements UserDetailsService {
    private final  UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username).orElseThrow();
        return new User(user.getUsername(), user.getPassword(), List.of());
    }
}
