package com.exchange.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.Principal;
import java.util.*;

/**
 * Created by greenlucky on 3/21/17.
 */
@RestController
public class AuthorizeHandler {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeHandler.class);

    @Autowired
    private AuthorizationServerEndpointsConfiguration configuration;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @RequestMapping("/me")
    public Map<String, String> me(Principal principal) {

        Map<String, String> map = new LinkedHashMap<>();
        if (principal != null) {
            map.put("name", principal.getName());
        }
        return map;
    }

    @GetMapping("/unauthorized")
    public ResponseEntity<Object> unauthorized() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("error", "unauthorized");
        map.put("error_description", "Full authentication is required to access this resource");
        return new ResponseEntity<Object>(map, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/token")
    public OAuth2AccessToken token(Principal principal) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Map<String, String> requestParameters = new HashMap<>();

        boolean approved = true;
        Set<String> scope = new HashSet<>();
        scope.add("scope");
        Set<String> resourceIds = new HashSet<>();
        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");
        Map<String, Serializable> extensionProperties = new HashMap<>();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId,
                authorities, approved, scope, resourceIds,
                null, responseTypes, extensionProperties);
        User userPrincipal = new User(principal.getName(), "", true, true, true, true, authorities);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
        AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();
        OAuth2AccessToken token = tokenService.createAccessToken(auth);

        LOGGER.info("Generate access token {} for {}", token, principal.getName());
        
        return token;
    }
}
