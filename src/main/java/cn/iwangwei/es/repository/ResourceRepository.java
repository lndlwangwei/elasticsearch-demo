package cn.iwangwei.es.repository;

import cn.iwangwei.es.bean.Resource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ResourceRepository extends ElasticsearchRepository<Resource, Integer> {
    List<Resource> getAllByTitle(String title);
}
