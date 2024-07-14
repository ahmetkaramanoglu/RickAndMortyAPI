package com.rickandmortyapi.rickandmorty.security;

import com.rickandmortyapi.rickandmorty.service.JwtService;
import com.rickandmortyapi.rickandmorty.service.UserService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Burda ise Token kontrolleri saglaniyor.
@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    //tokeni valid ama authorize degil. yetkin yok.

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtAuthFilter.doFilterInternal");
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if( authHeader != null && authHeader.startsWith("Bearer ")) {
            System.out.println("JwtAuthFilter.doFilterInternal: authHeader: " + authHeader);
            token = authHeader.substring(7);
            userName = jwtService.extractUser(token);
        }
        //burda throw gonderilebilir mi? gerek yok.
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("JwtAuthFilter.doFilterInternal: userName: " + userName);
            UserDetails user = userService.loadUserByUsername(userName);
            if(jwtService.validateToken(token,user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,
                        null,user.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
