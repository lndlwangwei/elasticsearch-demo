package cn.iwangwei.test

import cn.iwangwei.BaseTest
import cn.iwangwei.es.bean.Resource
import cn.iwangwei.es.service.ResourceService
import org.springframework.beans.factory.annotation.Autowired

class SearchGTests extends BaseTest {

    @Autowired
    ResourceService resourceService;

    def "test"() {
        expect:
        Resource resource = new Resource();

        resource.setId(new Random().nextInt())
        resource.setTitle("test 11")
        resource.setContent("content " + UUID.randomUUID())

        resourceService.save(resource)
    }
}
