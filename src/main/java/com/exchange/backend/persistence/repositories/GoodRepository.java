package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 */

public interface GoodRepository extends PagingAndSortingRepository<Good, String> {

    List<Good> findByIdIn(List<String> ids);

    List<Good> findAll();

    Page<Good> findAll(Pageable pageable);

}
