package com.exchange.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Collection;

/**
 * Created by Mrs Hoang on 18/12/2016.
 */
@Service
public class UserAuthenticationService implements AuthenticationManager {

    private static final int NUMBER_STRENGTH = 12;

    private static final String SALT = "kaj398498(*(&$&#&*&*#";

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(NUMBER_STRENGTH, new SecureRandom(SALT.getBytes()));
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = userService.getOne(email);

        if (user == null || !user.getUsername().equalsIgnoreCase(email)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (password == null) {
            throw new BadCredentialsException("Wrong password.");
        }

        if (!passwordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}
