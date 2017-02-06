package com.exchange.backend.persistence.repositories;

import com.exchange.backend.persistence.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by greenlucky on 1/24/17.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

}
