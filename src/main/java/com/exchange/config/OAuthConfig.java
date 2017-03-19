package com.exchange.config;

import com.exchange.backend.service.UserAuthenticationService;
import com.exchange.restapi.SearchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * Created by optimize on 2/12/17.
 */
@Configuration
public class OAuthConfig {

    @Configuration
    @EnableWebSecurity
    protected static class ResourceServer extends WebSecurityConfigurerAdapter {

        @Autowired
        private Environment env;

        @Override
        public void configure(WebSecurity web) throws Exception {
            web
                    .ignoring()
                    .antMatchers("/resource/**");
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


        @Override
        public void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable();
            http
                    .anonymous().and()
                    .authorizeRequests()
                    .antMatchers(SearchHandler.REST_API_SEARCH + "/**", "/user/current").permitAll()
                    .anyRequest().authenticated();
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

        static final int TOKEN_VALID_TIME = 6000;
        static final int REFRESH_TOKEN_VALID_TIME = 24 * 6000;

        @Autowired
        private UserAuthenticationService authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
            security.checkTokenAccess("anonymous").checkTokenAccess("isAuthenticated");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("default")
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("read", "write", "trust")
                    .resourceIds("oauth2-resource")
                    .refreshTokenValiditySeconds(REFRESH_TOKEN_VALID_TIME)
                    .accessTokenValiditySeconds(TOKEN_VALID_TIME);
        }
    }
}
