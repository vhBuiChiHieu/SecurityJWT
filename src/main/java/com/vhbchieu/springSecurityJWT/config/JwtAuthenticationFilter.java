package com.vhbchieu.springSecurityJWT.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//filter class
//OncePerRequestFilter đảm bảo bô
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    //chi goi 1 lan moi yeu cau
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain         //
    ) throws ServletException, IOException {
        //dau tien kiem tra ton tai cua jwt u header
        final String authorHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        //jwt luan bat dau voi Bearer
        if (authorHeader == null || !authorHeader.startsWith("Bearer")){
            //dua toi filter tiep theo
            filterChain.doFilter(request, response);
            return;
        } else {
            //lay token, bat dau sau Bearer
            jwt = authorHeader.substring(7);
            //extract email tu jwt
            userEmail = jwtService.extractUsername(jwt);
        }
    }
}
