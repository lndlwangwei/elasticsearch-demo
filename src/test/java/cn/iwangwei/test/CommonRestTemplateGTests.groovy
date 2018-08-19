package cn.iwangwei.test

import cn.iwangwei.BaseTest

class CommonRestTemplateGTests extends BaseTest {

    /**
     * 其实用getForObject也可以，
     * 但是需要传递请求体设置查询条件
     * @return
     */
    def '获取文档数量'() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "twitter/doc/_count", "{\n" +
                "    \"query\": {\n" +
                "        \"match_all\": {}\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }
}
