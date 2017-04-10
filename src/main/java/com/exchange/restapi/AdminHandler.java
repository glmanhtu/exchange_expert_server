package com.exchange.restapi;

import com.exchange.backend.Roles;
import com.exchange.backend.datatype.search.AdminSearchGood;
import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.enums.StatusEnum;
import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.domain.Status;
import com.exchange.backend.persistence.dto.DataWrapper;
import com.exchange.backend.persistence.dto.GoodDto;
import com.exchange.backend.service.GoodService;
import com.exchange.restapi.request.GoodStatus;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.exchange.restapi.AdminHandler.REST_API_ADMIN;

/**
 * Created by optimize on 3/15/17.
 */
@RestController
@RequestMapping(REST_API_ADMIN)
@EnableResourceServer
@Secured(Roles.USER)
public class AdminHandler {
    public static final String REST_API_ADMIN = "/admin";

    @Autowired
    private GoodService goodService;


    @RequestMapping(value = "/good/status", method = RequestMethod.POST)
    public ResponseEntity<?> status(@RequestBody GoodStatus goodStatus) {
        Good good = goodService.getOne(goodStatus.getGoodId());
        if (good != null) {
            good.setStatus(new Status(StatusEnum.convert(goodStatus.getStatus())));
            goodService.update(good);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        Message message = new Message(MessageEnum.GOODS_NOT_FOUND);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/search/good", method = RequestMethod.POST)
    public ResponseEntity<?> searchGood(@RequestBody AdminSearchGood searchGood) {
        Sort.Direction sort = Sort.Direction.ASC;
        if (!searchGood.getOrder().getASC()) {
            sort = Sort.Direction.DESC;
        }
        PageRequest pageRequest = new PageRequest(
                searchGood.getPagination().getCurrentPage(),
                searchGood.getPagination().getItemsPerPage(),
                new Sort(new Sort.Order(sort, searchGood.getOrder().getBy()))
        );
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        queryBuilder.must(QueryBuilders.matchQuery("status.name", StatusEnum.convert(searchGood.getStatus())));
        if (searchGood.getTitle() != null) {
            queryBuilder.must(QueryBuilders.matchQuery("title", searchGood.getTitle()));
        }
        if (searchGood.getCategory() != null) {
            queryBuilder.must(QueryBuilders.termQuery("type.name", searchGood.getCategory()));
        }
        if (searchGood.getDistance() != null) {
            queryBuilder.must(QueryBuilders.geoDistanceQuery("location")
                    .point(searchGood.getLocation().getLat(), searchGood.getLocation().getLng())
                    .distance(searchGood.getDistance(), DistanceUnit.METERS)
            );
        }
        if (searchGood.getPrice() != null) {
            queryBuilder.must(QueryBuilders.rangeQuery("price")
                    .from(searchGood.getPrice().getFrom())
                    .to(searchGood.getPrice().getTo())
            );
        }
        if (searchGood.getSeller() != null) {
            queryBuilder.must(QueryBuilders.termQuery("postBy.id", searchGood.getSeller()));
        }

        List<GoodDto> goodDtos = new ArrayList<>();

        DataWrapper<Good, ElasticGood> goods = goodService.findAll(queryBuilder, pageRequest);

        goods.getContent().forEach(good -> goodDtos.add(new GoodDto(good)));

        Page<GoodDto> response = new PageImpl<>(goodDtos, pageRequest, goods.getTotalElements());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
