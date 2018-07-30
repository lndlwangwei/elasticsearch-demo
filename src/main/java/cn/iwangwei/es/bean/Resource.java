package cn.iwangwei.es.bean;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "rbm", type = "resource", shards = 1, replicas = 0, refreshInterval = "-1")
public class Resource {

    private int id;

    private String title;

    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
