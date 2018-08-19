package cn.iwangwei.test

import cn.iwangwei.BaseTest

class GetRestTemplateGTests extends BaseTest {

    def "获取指定id的文档"() {
        expect:
        String response = restTemplate.getForObject(BASE_URL + "twitter/doc/99", String.class);
        print(response)
    }

    def "get example 1"() {
        expect:
        String response = restTemplate.getForObject(BASE_URL + "/megacorp/employee/1", String.class);
        print(response)
    }
}
