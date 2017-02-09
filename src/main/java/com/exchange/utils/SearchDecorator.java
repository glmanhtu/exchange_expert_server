package com.exchange.utils;

/**
 * Created by optimize on 2/9/17.
 */
public abstract class SearchDecorator implements Search {

    private Search search;

    public SearchDecorator(Search search) {
        this.search = search;
    }

    public Search getSearch() {
        return search;
    }
}
