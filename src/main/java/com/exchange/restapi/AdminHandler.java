package com.exchange.restapi;

import com.exchange.backend.datatype.search.AdminSearchGood;
import com.exchange.backend.enums.StatusEnum;
import com.exchange.backend.persistence.dto.SimpleGoodDto;
import com.exchange.backend.service.GoodService;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.exchange.restapi.AdminHandler.REST_API_ADMIN;

/**
 * Created by optimize on 3/15/17.
 */
@RestController
@RequestMapping(REST_API_ADMIN)
public class AdminHandler {
    public static final String REST_API_ADMIN = "/admin";

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/search/good")
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
        List<SimpleGoodDto> simpleGoodDtos = new ArrayList<>();
        goodService.findAll(queryBuilder, pageRequest).forEach(good -> simpleGoodDtos.add(new SimpleGoodDto(good)));
        return new ResponseEntity<>(simpleGoodDtos, HttpStatus.OK);
    }
}
