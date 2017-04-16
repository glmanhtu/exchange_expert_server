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

    private static final Integer CODE_302 = 302;
    private static final Integer CODE_303 = 303;

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
        message.setCode(CODE_302);
        message.setMessage(e.getMessage());
        return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalException.class)
    public ResponseEntity<Object> illegalException(IllegalException e) {
        Message message = new Message();
        message.setCode(e.getCode());
        message.setMessage(e.getMessage());
        return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
    }
}
