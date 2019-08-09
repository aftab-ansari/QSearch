package com.quikr.qsearch.repository;


import com.quikr.qsearch.model.MessageES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageESRepository extends ElasticsearchRepository<MessageES, Long> {

    public List<MessageES> findByText(String text);
}
