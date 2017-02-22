package com.exchange.restapi;

import com.exchange.backend.datatype.search.SearchGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.dto.SimpleGoodDto;
import com.exchange.backend.persistence.dto.UserDto;
import com.exchange.backend.service.GoodService;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by glmanhtu on 2/15/17.
 */
@RestController
@RequestMapping(SearchHandler.REST_API_USER)
public class SearchHandler {

    public static final String REST_API_USER = "/search";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public ResponseEntity<?> searchGood(@RequestBody SearchGood searchGood) {
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
        List<SimpleGoodDto> simpleGoodDtos = goodService.findAll(queryBuilder, pageRequest).stream()
                .map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(simpleGoodDtos, HttpStatus.OK);
    }

    private SimpleGoodDto convertToDto(Good elasticGood) {
        SimpleGoodDto simpleGoodDto = modelMapper.map(elasticGood, SimpleGoodDto.class);
        UserDto userDto = modelMapper.map(elasticGood.getPostBy(), UserDto.class);
        userDto.setAvgRating(elasticGood.getPostBy().getRating().getAvg());
        simpleGoodDto.setSeller(userDto);
        return simpleGoodDto;
    }

}
