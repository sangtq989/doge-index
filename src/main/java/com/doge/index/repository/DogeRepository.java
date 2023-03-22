package com.doge.index.repository;

import com.doge.index.entity.Doge;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogeRepository extends MongoRepository<Doge, String> {
    @Aggregation({
            "{$match: {user_id: ?0}}",
            "{$search: {index: 'doge_index', text: {query: ?1, path: {wildcard: '*'}}}}"
    })
    Doge findByUserAndKeywords(String recipientId,List<String> keywords);
    @Aggregation({
            "{$search: {index: 'doge_index', text: {query: ?0, path: {wildcard: '*'}}}}"
    })
    List<Doge> findByUserAndKeywords(List<String> keywords);
}
