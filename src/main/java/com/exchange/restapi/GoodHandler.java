package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.dto.GoodDto;
import com.exchange.backend.persistence.dto.SimpleGoodDto;
import com.exchange.backend.service.BadWordFilterService;
import com.exchange.backend.service.GoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Mrs Hoang on 12/02/2017.
 */
@RestController
@RequestMapping(value = GoodHandler.REST_API_GOODS)
public class GoodHandler {

    /**
     * The application logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodHandler.class);

    public static final String REST_API_GOODS = "/goods";

    @Autowired
    private GoodService goodService;

    /**
     * Creates a new good given by good
     *
     * @param good
     * @return Message after create
     * @see Good
     */

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> createGoods(@RequestBody Good good) {

        LOGGER.info("Creating goods {}", good);
        String badWordTitle = BadWordFilterService.filterText(good.getTitle());
        String badWordDes = BadWordFilterService.filterText(good.getDescription());
        Message message = null;

        if (badWordTitle != null) {
            LOGGER.info("The title goods can not contain {}", badWordTitle);
            message = new Message(MessageEnum.BAD_WORD_TITLE, badWordTitle);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (badWordDes != null) {
            LOGGER.info("The description goods can not contain {}", badWordDes);
            message = new Message(MessageEnum.BAD_WORD_DES, badWordDes);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        //create new good
        good = goodService.create(good);

        SimpleGoodDto simpleGoodDto = new SimpleGoodDto(good);

        LOGGER.info("Created goods {}", good);

        return new ResponseEntity<>(simpleGoodDto, HttpStatus.OK);
    }

    /**
     * Updates goods given by goodId and Good
     *
     * @param goodId
     * @param good
     * @return GoodDto after update
     */
    @RequestMapping(value = "/{goodId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateGoods(@PathVariable String goodId, @RequestBody Good good) {

        Good localGood = goodService.getOne(goodId);

        Message message = null;
        if (localGood == null) {
            message = new Message(MessageEnum.GOODS_NOT_FOUND, goodId);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Creating goods {}", good);
        String badWordTitle = BadWordFilterService.filterText(good.getTitle());
        String badWordDes = BadWordFilterService.filterText(good.getDescription());


        if (badWordTitle != null) {
            LOGGER.info("The title goods can not contain {}", badWordTitle);
            message = new Message(MessageEnum.BAD_WORD_TITLE, badWordTitle);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (badWordDes != null) {
            LOGGER.info("The description goods can not contain {}", badWordDes);
            message = new Message(MessageEnum.BAD_WORD_DES, badWordDes);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        //create new good
        good = goodService.update(good);

        SimpleGoodDto simpleGoodDto = new SimpleGoodDto(good);

        LOGGER.info("Created goods {}", good);

        return new ResponseEntity<>(simpleGoodDto, HttpStatus.OK);
    }


    @GetMapping("/user/{ofUser}")
    public ResponseEntity<Object> getGoodsOfUser(@PathVariable String ofUser, Pageable pageable) {
        Page<Good> goods = goodService.getGoodsOfUser(ofUser, pageable);
        return new ResponseEntity<Object>(goods, HttpStatus.OK);
    }


    /**
     * Gets Goods given by (category slug) type slug or slug of goods
     *
     * @param slug
     * @return A Good or message enum Goods not found good not found if not found
     * @see MessageEnum
     */
    @RequestMapping(value = "/{categorySlug}/{slug}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBySlug(@PathVariable String categorySlug, @PathVariable String slug) {
        Good good = goodService.getByCategorySlugAndSlug(categorySlug, slug);
        if (good == null) {
            Message message = new Message(MessageEnum.GOODS_NOT_FOUND);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        GoodDto goodDto = new GoodDto(good);
        return new ResponseEntity<>(goodDto, HttpStatus.OK);
    }


}
