package com.exchange.config;

import com.exchange.backend.repositories.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Created by greenlucky on 1/14/17.
 */
@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * They key enscryption password
     */
    private static final String SALT = "kaj398498(*(&$&#&*&*#";
    private static final int TOKEN_VALIDITY_SECONDS = 31536000;
    private static final int NUMBER_STRENGTH = 12;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(NUMBER_STRENGTH, new SecureRandom(SALT.getBytes()));
    }

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;

    /**
     * PUBLIC URLS
     */
    private static final String[] PUBLIC_MATCHES = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/sources/**",
            "/",
            "/register/**",
            "/console/**",
            "/error/**",
            "/api/**",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> activeProflies = Arrays.asList(env.getActiveProfiles());
        if (activeProflies.contains("dev")) {
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }

        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHES).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/")
                .failureUrl("/login?error=true").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .rememberMe()
                .rememberMeCookieName("REMEMBER_ME_EXCHANGE_EXPERT")
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }
}
