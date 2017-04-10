package com.exchange.backend.persistence.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

/**
 * Created by optimize on 4/10/17.
 */
public class DataWrapper<T, P> implements Page<T> {

    private List<T> data;

    private Page<P> page;

    public DataWrapper(List<T> data, Page<P> page) {
        this.data = data;
        this.page = page;
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public int getNumber() {
        return page.getNumber();
    }

    @Override
    public int getSize() {
        return page.getSize();
    }

    @Override
    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }

    @Override
    public List<T> getContent() {
        return data;
    }

    @Override
    public boolean hasContent() {
        return data.size() > 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean isFirst() {
        return page.isFirst();
    }

    @Override
    public boolean isLast() {
        return page.isLast();
    }

    @Override
    public boolean hasNext() {
        return page.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    @Override
    public Pageable nextPageable() {
        return page.nextPageable();
    }

    @Override
    public Pageable previousPageable() {
        return page.previousPageable();
    }

    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }
}
