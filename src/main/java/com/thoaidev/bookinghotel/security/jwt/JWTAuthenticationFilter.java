package com.thoaidev.bookinghotel.security.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

public class JWTAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JWTGenerator tokenGenerator;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    
    private static final List<String> PUBLIC_URLS = List.of(
    "/api/auth/dang-nhap",
    "/api/auth/dang-ky",
    "/api/auth/quen-mat-khau"
);
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
                                    throws ServletException, IOException {
        String path = request.getRequestURI();

        // BỎ QUA CÁC ĐƯỜNG DẪN KHÔNG CẦN XÁC THỰC
        if (path.startsWith("/api/auth/")) {
        filterChain.doFilter(request, response);
        return;
    }

        String token = getJWTFromRequest(request);
        System.out.println("this JWTAuthenticationFilter");
        System.out.println("==println(JWTAuthenticationFilter)TOKEN >>> " + token);

        if(StringUtils.hasText(token) && tokenGenerator.validateToken(token)){
            String username = tokenGenerator.getUsernameFromJWT(token);

            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
            (
                    userDetails,
                    null,
                    userDetails.getAuthorities()
              );
            usernamePasswordAuthenticationToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(SecurityConstant.AUTHORIZATION_HEADER);
        System.out.println("==println(JWTAuthenticationFilter) Authorization Header: " + bearerToken);
        if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith(SecurityConstant.AUTHORIZATION_PREFIX)){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
