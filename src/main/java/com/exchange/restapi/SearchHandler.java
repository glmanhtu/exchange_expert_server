package com.exchange.restapi;

import com.exchange.backend.datatype.search.SearchGood;
import com.exchange.backend.enums.StatusEnum;
import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.User;
import com.exchange.backend.persistence.dto.SimpleGoodDto;
import com.exchange.backend.service.GoodService;
import com.exchange.backend.service.UserService;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glmanhtu on 2/15/17.
 */
@RestController
@RequestMapping(SearchHandler.REST_API_SEARCH)
public class SearchHandler {

    /**
     * The application logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchHandler.class);

    public static final String REST_API_SEARCH = "/search";

    private static final int DEFAULT_NUMBER_ITEM_PER_PAGE = 5;

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public ResponseEntity<?> searchGood(@RequestBody SearchGood searchGood, Principal principal) {
        LOGGER.info("Search good {}", searchGood);
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
        queryBuilder.must(QueryBuilders.matchQuery("status.name", StatusEnum.TRADING.getName()));
        if (principal != null) {
            User user = userService.getOne(principal.getName());
            queryBuilder.mustNot(QueryBuilders.termsQuery("post_by.id", user.getExcluded()));
        }
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

    /**
     * Suggest search google given by title.
     *
     * @param title
     * @return A list of google or null if not found
     */
    @GetMapping(value = "/good")
    public ResponseEntity<Object> suggestSearchGoodsByTitle(@RequestParam String title) {
        List<ElasticGood> elasticGoods = null;
        if (title != null) {

            PageRequest pageRequest = new PageRequest(0, DEFAULT_NUMBER_ITEM_PER_PAGE,
                    new Sort(new Sort.Order(Sort.Direction.ASC, "title")));
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

            queryBuilder.must(QueryBuilders.matchQuery("title", title));
            elasticGoods = goodService.findGoodsByQuery(queryBuilder, pageRequest);
        }

        return new ResponseEntity<Object>(elasticGoods, HttpStatus.OK);
    }

}
