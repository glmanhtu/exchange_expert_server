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
@RequestMapping(RestApiGoods.REST_API_GOODS)
public class RestApiGoods {

    /** The application logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiGoods.class);

    public static final String REST_API_GOODS = "/goods";

    @Autowired
    private GoodService goodService;

    @Autowired
    private I18NService i18NService;

    private List<MessageDTO> messageDTOS;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createGoods(@RequestBody Good good, Locale locale) {

        //construct messageDTOs
        messageDTOS = new ArrayList<>();

        good.setStatus(new Status(StatusEnum.PENDING));

        messageDTOS.add(new MessageDTO(MessageType.SUCCESS,
                i18NService.getMessage("goods.create.success.text", locale)));

        return messageDTOS;
    }
}
