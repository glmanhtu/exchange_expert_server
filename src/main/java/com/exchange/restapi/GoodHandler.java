package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.enums.StatusEnum;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.domain.Status;
import com.exchange.backend.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

        //create good id given by user id and good slug
        String goodId = good.getPostBy().getId() + good.getSlug();

        //checking good slug is existed
        if (goodService.inValidSlug(good.getSlug())) {

            LOGGER.error("The slug {} is invalid", good.getSlug());
            Message message = new Message(MessageEnum.GOODS_SLUG_INVALID);
            return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
        }

        good.setStatus(new Status(StatusEnum.PENDING));

        //create new good
        good = goodService.create(good);

        LOGGER.info("Created goods {}", good);

        return new ResponseEntity<Object>(good, HttpStatus.OK);
    }
}
