package com.exchange.backend.service;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by optimize on 2/9/17.
 */
public interface SearchEverything<T> {
    Page<T> findAll(QueryBuilder queryBuilder, PageRequest pageRequest);
}
