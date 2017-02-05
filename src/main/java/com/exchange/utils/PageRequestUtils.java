package com.exchange.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by greenlucky on 1/24/17.
 */
public class PageRequestUtils {

    private PageRequestUtils() {
        throw new AssertionError("Not instantiable");
    }

    public static PageRequest createPageRequest(Pageable pageable) {
        Sort localSort = new Sort(Sort.Direction.DESC);
        return new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), localSort);
    }

    public static PageRequest createPageRequest(Pageable pageable, Sort sort) {
        return new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

}
