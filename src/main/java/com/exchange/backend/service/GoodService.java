package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.repositories.elasticsearch.ElasticGoodRepository;
import com.exchange.backend.persistence.repositories.mongodb.GoodRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 *
 * @version %I%D%
 */
@Service
public class GoodService implements SearchEverything<Good> {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private ElasticGoodRepository elasticGoodRepository;

    /**
     * Creates a new good given by good
     * @param good
     * @return A good after created
     * @see Good
     */
    public Good create(Good good) {

        good.setPostDate(LocalDateTime.now(Clock.systemDefaultZone()));

        return goodRepository.save(good);
    }

    /**
     * Updates a good given by good
     * @param good
     * @return A good after updated
     * @see Good
     */
    public Good update(Good good) {
        return goodRepository.save(good);
    }

    /**
     * Deletes a good given by goodId
     * @param goodId
     * @see Good
     */
    public void delete(String goodId) {
        goodRepository.delete(goodId);
    }

    /**
     * Retrieves a good given by goodId or null if not found
     * @param goodId
     * @return A good or null if not found
     * @see Good
     */
    public Good getOne(String goodId) {
        return goodRepository.findOne(goodId);
    }

    /**
     * Retrieves a list goods or null if not exist
     * @return A list of good or null if not exist
     */
    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    /**
     * Retrieves a page of good or page's content is null if not exist
     * @param pageable
     * @return A page of good or a page with null content if not exist
     */
    public Page<Good> getAll(Pageable pageable) {
        return goodRepository.findAll(pageable);
    }

    @Override
    public List<Good> findAll(QueryBuilder queryBuilder, PageRequest pageRequest) {
        List<ElasticGood> elasticGoods = elasticGoodRepository.search(queryBuilder, pageRequest).getContent();
        List<String> goodIds = new ArrayList<>(elasticGoods.size());
        for (ElasticGood elasticGood : elasticGoods) {
            goodIds.add(elasticGood.getId());
        }
        return goodRepository.findByIdIn(goodIds);
    }


    /**
     * Checking goodid is existed.
     *
     * @param goodId
     * @return True if good id is existed, otherwise false
     */
    public boolean inValidId(String goodId) {
        return goodRepository.exists(goodId);
    }
}
