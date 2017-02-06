package com.exchange.backend.repositories;

import com.exchange.backend.persistence.domain.Good;
import com.exchange.backend.persistence.repositories.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by greenlucky on 1/31/17.
 *
 * @version %I%D%
 */
@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private CounterService counterService;

    /**
     * Creates a new good given by good
     * @param good
     * @return A good after created
     * @see Good
     */
    public Good create(Good good) {
       /* if(good != null)
            good.setId(counterService.getNextSequence("goods"));*/
        return goodRepository.save(good);
    }

    /**
     * Updates a good given by good
     * @param good
     * @return A good after updated
     * @see Good
     */
    public Good update(Good good) {
        return goodRepository.save(good);
    }

    /**
     * Deletes a good given by goodId
     * @param goodId
     * @see Good
     */
    public void delete(long goodId) {
        goodRepository.delete(goodId);
    }

    /**
     * Retrieves a good given by goodId or null if not found
     * @param goodId
     * @return A good or null if not found
     * @see Good
     */
    public Good getOne(long goodId) {
        return goodRepository.findOne(goodId);
    }

    /**
     * Retrieves a list goods or null if not exist
     * @return A list of good or null if not exist
     */
    public List<Good> getAll() {
        return goodRepository.findAll();
    }

    /**
     * Retrieves a page of good or page's content is null if not exist
     * @param pageable
     * @return A page of good or a page with null content if not exist
     */
    public Page<Good> getAll(Pageable pageable) {
        return goodRepository.findAll(pageable);
    }

}
