package com.quikr.qsearch.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "qsearch", type = "MessageES", shards = 2)
public class MessageES {
    @Id
    private Long id;
    private String text;

    public MessageES() {
    }

    public MessageES(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    public void setText(String text) {
        this.text = text;
    }
}
