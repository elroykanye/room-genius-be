package com.sharonghranui.roomgenius.configuration.filter;


import com.sharonghranui.roomgenius.business.service.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            var token= request.getHeader("Authorization");
            if (!token.startsWith("Bearer ")){
                SecurityContextHolder.getContext().setAuthentication(null);
                filterChain.doFilter(request, response);
                return;
            }
            var authentication = getAuthentication(request, token.replace("Bearer ", ""));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch(Exception ignored){
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(HttpServletRequest request, String token) {
        var username = JwtUtil.username(token);
        var auth = new UsernamePasswordAuthenticationToken(username, null, List.of());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return auth;
    }
}
