package com.exchange.backend.datatype;

/**
 * Created by glmanhtu on 2/15/17.
 */
public class Pagination {
    private int currentPage;
    private int itemsPerPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
