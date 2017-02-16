package com.exchange.backend.datatype.search;

import com.exchange.backend.datatype.Filter;
import com.exchange.backend.datatype.Location;
import com.exchange.backend.datatype.Order;
import com.exchange.backend.datatype.Pagination;
import com.exchange.backend.datatype.Price;

/**
 * Created by glmanhtu on 2/15/17.
 */
public class SearchGood {
    private Pagination pagination;
    private Location location;
    private Integer distance;
    private String title;
    private String category;
    private String seller;
    private Filter filter;
    private Price price;
    private Order order;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
