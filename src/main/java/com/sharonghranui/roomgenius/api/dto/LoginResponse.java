package com.sharonghranui.roomgenius.api.dto;

public record LoginResponse(
        String token,
        String username
) implements Payload {}
