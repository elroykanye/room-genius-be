package com.sharonghranui.roomgenius.business.manager;

import com.sharonghranui.roomgenius.api.dto.LoginRequest;
import com.sharonghranui.roomgenius.api.dto.LoginResponse;
import com.sharonghranui.roomgenius.business.service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ) ;
        try {
            var authentication = authenticationManager.authenticate(authenticationToken);
            if (authentication.isAuthenticated()) {
                return new LoginResponse(
                        JwtUtil.build(request.username()),
                        request.username()
                );
            }
            throw new AuthenticationCredentialsNotFoundException("User not auth");
        } catch (Exception e) {
            log.error("Invalid credentials");
            e.printStackTrace();
        }
        return null;
    }
}
