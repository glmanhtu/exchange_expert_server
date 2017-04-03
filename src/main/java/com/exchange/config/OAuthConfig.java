package com.exchange.config;

import com.exchange.backend.service.UserAuthenticationService;
import com.exchange.restapi.GoodHandler;
import com.exchange.restapi.SearchHandler;
import com.exchange.restapi.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by optimize on 2/12/17.
 */
@Configuration
@EnableWebSecurity
public class OAuthConfig extends WebSecurityConfigurerAdapter {


    private static final int ORDER_NUMBER = -100;

   /* @Autowired
    private Environment env;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(ORDER_NUMBER);
        return registration;
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resource/**")
                .antMatchers(HttpMethod.GET, GoodHandler.REST_API_GOODS + "/**")
                .antMatchers(UserHandler.REST_API_USER)
                .antMatchers(HttpMethod.GET, UserHandler.REST_API_USER + "/**");
    }

  /*  private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));
        filters.add(ssoFilter(google(), "/login/google"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    class ClientResources {

        @NestedConfigurationProperty
        private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }*/

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**")
                    .authorizeRequests()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers(SearchHandler.REST_API_SEARCH + "/**").permitAll()
                    .anyRequest().authenticated();
        }
    }

    /**
     * Mapping basic login to Oauth and vice versa
     */
    private static class OAuthRequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            return (auth != null) && auth.startsWith("Bearer");
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
            security.checkTokenAccess("anonymous").checkTokenAccess("isAuthenticated")
                    .allowFormAuthenticationForClients();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                    .withClient("default")
                    .authorizedGrantTypes("password", "refresh_token", "authorization_code", "implicit")
                    .scopes("read", "write", "trust", "openid")
                    .autoApprove(".*")
                    .resourceIds("oauth2-resource")
                    .refreshTokenValiditySeconds(REFRESH_TOKEN_VALID_TIME)
                    .accessTokenValiditySeconds(TOKEN_VALID_TIME);
        }

    }
}
