package com.exchange.backend.service;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by optimize on 2/9/17.
 */
public interface SearchEverything<T> {

    List<T> findAll(Predicate predicate);
}
