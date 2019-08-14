package com.quikr.qsearch.repository;


import com.quikr.qsearch.model.MessageES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageESRepository extends ElasticsearchRepository<MessageES, Long> {

}
