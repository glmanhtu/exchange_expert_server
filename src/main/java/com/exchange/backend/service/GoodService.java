package com.exchange.backend.service;

import com.exchange.backend.enums.StatusEnum;
import com.exchange.backend.persistence.domain.ElasticGood;
import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.domain.Status;
import com.exchange.backend.persistence.dto.DataWrapper;
import com.exchange.backend.persistence.repositories.elasticsearch.ElasticGoodRepository;
import com.exchange.backend.persistence.repositories.mongodb.GoodRepository;
import com.github.slugify.Slugify;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

        //Generate slug
        good.setSlug(generatedSlug(good.getTitle()));

        String categoryName = good.getCategory().getName();

        good.getCategory().setSlug(createSlug(categoryName));

        //create good id given by user id and good slug
        String goodId = good.getPostBy().getId() + "-" + good.getSlug();
        good.setId(goodId);
        good.setStatus(new Status(StatusEnum.PENDING));
        good.setPostDate(new Date().getTime());

        good = goodRepository.save(good);
        ElasticGood elasticGood = new ElasticGood();
        elasticGood.setDescription(good.getDescription());
        elasticGood.setId(good.getId());
        elasticGood.setLocation(good.getLocation());
        elasticGood.setPostBy(good.getPostBy());
        elasticGood.setPostDate(good.getPostDate());
        elasticGood.setPrice(good.getPrice());
        elasticGood.setSlug(good.getSlug());
        elasticGood.setTitle(good.getTitle());
        elasticGood.setCategory(good.getCategory());
        elasticGoodRepository.save(elasticGood);
        return good;
    }

    /**
     * Updates a good given by good
     * @param good
     * @return A good after updated
     * @see Good
     */
    public Good update(Good good) {
        elasticGoodRepository.save(good);
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
     * Gets Good given by slug
     * @param slug
     * @return A good or null if not found
     */
    public Good getBySlug(String slug) {
        return goodRepository.findBySlug(slug);
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
    public Page<Good> findAll(QueryBuilder queryBuilder, PageRequest pageRequest) {

        Iterable<ElasticGood> elasticGoods = elasticGoodRepository.search(queryBuilder);
        List<String> goodIds = new ArrayList<>();

        for (ElasticGood elasticGood : elasticGoods) {
            goodIds.add(elasticGood.getId());
        }

        return goodRepository.findByIdIn(goodIds, pageRequest);
    public DataWrapper<Good, ElasticGood> findAll(QueryBuilder queryBuilder, PageRequest pageRequest) {
        Page<ElasticGood> elasticGoods = elasticGoodRepository.search(queryBuilder, pageRequest);

        List<String> goodIds = new ArrayList<>(elasticGoods.getContent().size());
        for (ElasticGood elasticGood : elasticGoods) {
            goodIds.add(elasticGood.getId());
        }
        List<Good> goods = goodRepository.findByIdIn(goodIds);
        return new DataWrapper<>(goods, elasticGoods);
    }

    /**
     * Find goods by query builder.
     *
     * @param queryBuilder the query input
     * @param pageRequest
     * @return A list of elastic good or null if not found
     */
    public List<ElasticGood> findGoodsByQuery(QueryBuilder queryBuilder, PageRequest pageRequest) {
        List<ElasticGood> elasticGoods = elasticGoodRepository.search(queryBuilder, pageRequest).getContent();
        return elasticGoods;
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

    public boolean inValidSlug(String slug) {
        Good good = goodRepository.findBySlug(slug);
        if (good != null) {
            return true;
        }
        return false;
    }

    /**
     * creates slug given by title
     * @param title
     * @return slug
     */
    private String createSlug(String title) {
        Slugify slg = new Slugify();
        String slug = slg.slugify(title);
        return slug;
    }

    /**
     * Generates slug given by title
     * @param title
     * @return A slug
     */
    public String generatedSlug(String title) {

       String slug = createSlug(title);

        //checking valid slug
        slug = findValidSlug(slug);

        return slug;
    }


    /**
     * Finds valid slug given by slug
     * @param slug
     * @return A valid slug
     */
    public String findValidSlug(String slug) {
        //checking good slug is existed
        if (inValidSlug(slug)) {
            int i = 1;
           String slugTemp = slug + "-" + i;
           while (inValidSlug(slugTemp)) {
               i++;
               slugTemp = slug + "-" + i;
           }
           slug += "-" + i;
        }

        return slug;
    }

    /**
     * get Goods given by cagtegory slug (type slug) and slug of goods
     * @param categorySlug (type slug)
     * @param slug
     * @return A goods or null if not exist
     */
    public Good getByCategorySlugAndSlug(String categorySlug, String slug) {
        return goodRepository.findByCategorySlugAndSlug(categorySlug, slug);
    }
}
