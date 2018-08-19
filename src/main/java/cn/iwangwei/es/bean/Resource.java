package cn.iwangwei.es.bean;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Document(indexName = "rbm", type = "resource", shards = 1, replicas = 0, refreshInterval = "-1")
public class Resource {

    private int id;

    private String title;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss")
    private Date createTime1;

    private Instant instant;

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

    public Date getCreateTime1() {
        return createTime1;
    }

    public void setCreateTime1(Date createTime1) {
        this.createTime1 = createTime1;
    }


    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime1=" + createTime1 +
                ", instant=" + instant +
                '}';
    }
}
