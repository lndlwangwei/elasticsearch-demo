package cn.iwangwei.test

import cn.iwangwei.BaseTest
import cn.iwangwei.es.bean.Resource
import cn.iwangwei.es.repository.ResourceRepository
import cn.iwangwei.es.service.ResourceService
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder
import org.elasticsearch.index.query.QueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.beans.factory.annotation.Autowired

import java.time.Instant

class SearchGTests extends BaseTest {

    @Autowired
    ResourceService resourceService;
    @Autowired
    ResourceRepository repository;

    def "test Add Resource"() {
        expect:
        Resource resource = new Resource();

        resource.setId(new Random().nextInt())
        resource.setTitle("ssss3")
        resource.setContent("黄的岁昏2");
        resource.setCreateTime1(new Date())
        resource.setInstant(Instant.now())

        resourceService.save(resource)
    }

    def "test search Resource"() {
        expect:
        repository.findAll().each { resource -> println resource }
    }

    def "测试morelikethis"() {
        expect:
        List<Resource> resources = repository.findByContentLike("黄昏作反对者为")

        QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery("content", "黄昏作反对者为人", null)

//        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("content", "log4j2与log4j的作者为同一个人")

        MoreLikeThisQueryBuilder moreLikeThisQueryBuilder = new MoreLikeThisQueryBuilder("title", "content");

        moreLikeThisQueryBuilder.likeTexts("黄昏作反对者为人")

        Iterable<Resource> resources1 = repository.search(moreLikeThisQueryBuilder);



        resources1.each { resource -> println resource }

    }
}
