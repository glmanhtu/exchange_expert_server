package com.exchange.restapi;

import com.exchange.backend.enums.MessageType;
import com.exchange.backend.enums.StatusEnum;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.MessageDTO;
import com.exchange.backend.persistence.domain.Status;
import com.exchange.backend.service.GoodService;
import com.exchange.backend.service.I18NService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(GoodHandler.REST_API_GOODS)
public class GoodHandler {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodHandler.class);

    public static final String REST_API_GOODS = "/goods";

    @Autowired
    private GoodService goodService;

    @Autowired
    private I18NService i18NService;

    private List<MessageDTO> messageDTOS;

    /**
     * Creates a new good given by good
     * @param good
     * @param locale
     * @return MessageDTO after create.
     * @see MessageDTO
     * @see Locale
     * @see Good
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createGoods(@RequestBody Good good, Locale locale) {

        //construct messageDTOs
        messageDTOS = new ArrayList<>();

        LOGGER.info("Creating gooods {}", good);

        //create good id given by user id and good slug
        String goodId = good.getPostBy().getId() + good.getSlug();

        //checking good slug is existed
        if (goodService.inValidSlug(good.getSlug())) {

            LOGGER.error("The slug {} is invalid", good.getSlug());

            messageDTOS.add(new MessageDTO(MessageType.ERROR,
                    i18NService.getMessage("goods.error.slug.invalid.text", good.getSlug(), locale)));

            return new ResponseEntity<Object>(messageDTOS, HttpStatus.BAD_REQUEST);
        }

        good.setStatus(new Status(StatusEnum.PENDING));

        //create new good
        good = goodService.create(good);

        LOGGER.info("Created goods {}", good);

        messageDTOS.add(new MessageDTO(MessageType.SUCCESS,
                i18NService.getMessage("goods.create.success.text", good.getTitle(), locale)));

        return new ResponseEntity<Object>(messageDTOS, HttpStatus.OK);
    }
}
