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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Locale;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RestController
public class GoodHandler {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodHandler.class);

    public static final String REST_API_GOODS = "/goods";
    public static final String REST_API_GOOD_CREATE = REST_API_GOODS + "/create";

    @Autowired
    private GoodService goodService;

    /**
     * Creates a new good given by good
     * @param good
     * @param locale
     * @return Message after create.
     * @see Locale
     * @see Good
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createGoods(@RequestBody Good good, Locale locale) {

        LOGGER.info("Creating goods {}", good);

        //create new good
        good = goodService.create(good);

        LOGGER.info("Created goods {}", good);

        return new ResponseEntity<Object>(good, HttpStatus.OK);
    }

    @RequestMapping(value = REST_API_GOODS, method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        List<Good> goods = goodService.getAll();
        return new ResponseEntity<Object>(goods, HttpStatus.OK);
    }

    @RequestMapping(value = REST_API_GOODS + "/{goodId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll(@PathVariable String goodId) {
        Good good = goodService.getOne(goodId);
        if (good == null) {
            Message message = new Message(MessageEnum.GOODS_NOT_FOUND);
            return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(good, HttpStatus.OK);
    }
}
