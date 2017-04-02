package com.exchange.exceptions;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by greenlucky on 4/2/17.
 */
@RestController
@ControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnAuthorizeException.class)
    public ResponseEntity<Object> unAuthorizeExceptionHandler(UnAuthorizeException e) {
        Message message = new Message(MessageEnum.ACCESST_TOKEN_INVALID);
        return new ResponseEntity<Object>(message, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception e) {
        Message message = new Message();
        message.setCode(302);
        message.setMessage(e.getMessage());
        return new ResponseEntity<Object>(message, HttpStatus.UNAUTHORIZED);
    }

}
