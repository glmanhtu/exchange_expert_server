package com.exchange.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by greenlucky on 3/21/17.
 */
@RestController
public class AuthorizeHandler {

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
}
