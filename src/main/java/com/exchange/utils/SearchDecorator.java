package com.exchange.utils;

import com.exchange.backend.service.SearchEverything;

import javax.persistence.criteria.Predicate;

/**
 * Created by optimize on 2/9/17.
 */
public class SearchDecorator implements Search {

    protected Search search;

    public SearchDecorator(Search search) {
        this.search = search;
    }

    @Override
    public Predicate conditionals() {
        return search.conditionals();
    }

}
