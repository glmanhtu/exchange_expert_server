package com.exchange.utils.search;

/**
 * Created by optimize on 2/9/17.
 */
public class SearchGood {
    private SearchGood() {

    }

    public static <T> Specification<T> findByTitle(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }

    public static <T> Specification<T> findByDescription(String description) {
        return ((root, query, builder) -> builder.like(root.get("description"), "%" + description + "%"));
    }

    public static <T> Specification<T> findByPrice(float from, float to) {
        return ((root, query, builder) -> builder.between(root.get("price"), from, to));
    }
}
