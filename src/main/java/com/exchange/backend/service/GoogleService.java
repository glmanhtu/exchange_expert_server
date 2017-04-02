package com.exchange.backend.service;

import com.exchange.backend.persistence.dto.GoogleUserInfo;
import com.exchange.exceptions.UnAuthorizeException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by greenlucky on 4/2/17.
 */
@Service
public class GoogleService {

    private static final String URL_GOOGLE = "https://www.googleapis.com/oauth2/v3/userinfo";

    private Client client;

    public GoogleService() {
        this.client = Client.create();
    }

    public GoogleUserInfo getProfileGoogle(String accessToken) {

        GoogleUserInfo userInfo = null;
        
        ClientResponse response = getResponse(URL_GOOGLE, accessToken);

        userInfo = response.getEntity(GoogleUserInfo.class);


        return userInfo;
    }

    private ClientResponse getResponse(String url, String accessToken) {
        ClientResponse response = null;
        MultivaluedMap queryPrams = new MultivaluedMapImpl();
        queryPrams.add("access_token", accessToken);

        WebResource webResource = client.resource(url).queryParams(queryPrams);

        response = webResource.type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

        if ((!(response.getStatus() == 201 || response.getStatus() == 200))) {
            throw new UnAuthorizeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response;
    }
}
