package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 */
@Repository
public interface GoodRepository  extends PagingAndSortingRepository<Good, Long>{

    public List<Good> findAll();

    public Page<Good> findAll(Pageable pageable);
}
