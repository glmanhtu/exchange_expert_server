package com.exchange.backend.service;

import com.exchange.utils.search.Specification;

import java.util.List;

/**
 * Created by optimize on 2/9/17.
 */
public interface SearchEverything<T> {
    List<T> findAll(Specification<T> spec);
}