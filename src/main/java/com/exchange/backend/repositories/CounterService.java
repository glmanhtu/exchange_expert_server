package com.exchange.backend.repositories;

import com.exchange.backend.persistence.domain.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by greenlucky on 1/19/17.
 */
@Service
public class CounterService {

    @Autowired
    private MongoOperations mongo;

    public int getNextSequence(String collectionName) {
        Counter counter = mongo.findAndModify(query(where("_id").is(collectionName)),
                new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true), Counter.class);

        return counter.getSeq();
    }
}
