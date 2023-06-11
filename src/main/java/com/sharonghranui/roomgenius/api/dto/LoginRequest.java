package com.sharonghranui.roomgenius.api.dto;

public record LoginRequest(
        String username,
        String password
) {}