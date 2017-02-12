package com.exchange.backend.service;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by optimize on 2/9/17.
 */
public interface SearchEverything<T> {
    List<T> findAll(QueryBuilder queryBuilder, PageRequest pageRequest);
}
