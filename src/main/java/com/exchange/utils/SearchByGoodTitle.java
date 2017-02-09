package com.exchange.utils;

import javax.persistence.criteria.Predicate;

/**
 * Created by optimize on 2/9/17.
 */
public class SearchByGoodTitle extends SearchDecorator {
    public SearchByGoodTitle(Search search) {
        super(search);
    }

    @Override
    public Predicate conditionals() {
        return getSearch().conditionals();
    }
}
