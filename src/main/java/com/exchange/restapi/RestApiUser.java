package com.exchange.restapi;

import com.exchange.backend.enums.MessageType;
import com.exchange.backend.persistence.domain.MessageDTO;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.service.I18NService;
import com.exchange.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RestController
@RequestMapping(RestApiUser.REST_API_USER_INFO)
public class RestApiUser {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiUser.class);
    
    public static final String REST_API_USER_INFO = "/user";

    @Autowired
    private UserService userService;

    @Autowired
    private I18NService i18NService;

    private List<MessageDTO> messageDTOS;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getUser(@PathVariable String id, Locale locale){

        //construct messageDTOs
        messageDTOS = new ArrayList<>();

        User user = userService.getOne(id);

        //if user is null return message not found
        if(user == null){
            messageDTOS.add(new MessageDTO(MessageType.ERROR, i18NService.getMessage("user.id.not.found.text", id, locale)));
            return messageDTOS;
        }
        return user;
    }
}
