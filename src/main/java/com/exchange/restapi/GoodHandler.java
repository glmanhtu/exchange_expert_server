package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RestController
@RequestMapping(value = GoodHandler.REST_API_GOODS)
public class GoodHandler {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodHandler.class);

    public static final String REST_API_GOODS = "/goods";

    @Autowired
    private GoodService goodService;

    /**
     * Creates a new good given by good
     * @param good
     * @return Message after create
     * @see Good
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createGoods(@RequestBody Good good) {

        LOGGER.info("Creating goods {}", good);

        //create new good
        good = goodService.create(good);

        LOGGER.info("Created goods {}", good);

        return new ResponseEntity<>(good, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        List<Good> goods = goodService.getAll();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @RequestMapping(value = "/{goodId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll(@PathVariable String goodId) {
        Good good = goodService.getOne(goodId);
        if (good == null) {
            Message message = new Message(MessageEnum.GOODS_NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(good, HttpStatus.OK);
    }
}
