package com.sharonghranui.roomgenius.api.controller;

import com.sharonghranui.roomgenius.api.dto.LoginRequest;
import com.sharonghranui.roomgenius.api.dto.LoginResponse;
import com.sharonghranui.roomgenius.business.manager.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/login")
    protected LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }

    @GetMapping("/hello")
    protected String hello() {return "Hello";}
}
