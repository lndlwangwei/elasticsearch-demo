package cn.iwangwei.test

import cn.iwangwei.BaseTest
import cn.iwangwei.es.bean.Resource
import cn.iwangwei.es.repository.ResourceRepository
import cn.iwangwei.es.service.ResourceService
import org.springframework.beans.factory.annotation.Autowired

class SearchGTests extends BaseTest {

    @Autowired
    ResourceService resourceService;
    @Autowired
    ResourceRepository repository;

    def "test Add Resource"() {
        expect:
        Resource resource = new Resource();

        resource.setId(new Random().nextInt())
        resource.setTitle("ssss1")
        resource.setContent("黄的岁昏");

        resourceService.save(resource)
    }

    def "test search Resource"() {
        expect:
        repository.findAll().each { resource -> println resource }
    }

    def "测试morelikethis"() {
        expect:
        List<Resource> resources = repository.findByContentLike("黄昏作反对者为")

        resources.each { resource -> println resource }
    }
}
