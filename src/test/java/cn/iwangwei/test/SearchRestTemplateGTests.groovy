package cn.iwangwei.test

import cn.iwangwei.BaseTest

class SearchRestTemplateGTests extends BaseTest {
/**
 * 搜索index为megacorp,type为employee下的所有文档
 * @return
 */
    def "simplest search"() {
        expect:
        String res = restTemplate.getForObject(BASE_URL + "megacorp/employee/_search", String.class)
        print(res)
    }

    def "query by query-string"() {
        expect:
        String res = restTemplate.getForObject(BASE_URL + "megacorp/employee/_search?q=last_name:Smith", String.class)
        print(res)
    }
/**
 * query-string的方式很简单，但是比较局限，且不能完成较为复杂的查询，此时就需要用到查询表示式了
 * 下面这个查询完成了与上面的完全相同的查询功能
 */
    def "通过查询表达式进行查询"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"last_name\" : \"Smith\"\n" +
                "        }\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }

    def "较为复杂的查询1"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"query\" : {\n" +
                "        \"bool\": {\n" +
                "            \"must\": {\n" +
                "                \"match\" : {\n" +
                "                    \"last_name\" : \"smith\" \n" +
                "                }\n" +
                "            },\n" +
                "            \"filter\": {\n" +
                "                \"range\" : {\n" +
                "                    \"age\" : { \"gt\" : 30 } \n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }
/**
 * 搜索about字段与”rock climbing“相似的文档
 * @return
 */
    def "全文检索"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"about\" : \"rock climbing\"\n" +
                "        }\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }
/**
 * 搜索abount字段包含”rock climbing“的文档
 * @return
 */
    def "短语搜索"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"query\" : {\n" +
                "        \"match_phrase\" : {\n" +
                "            \"about\" : \"rock climbing\"\n" +
                "        }\n" +
                "    }\n" +
                "}" +
                "}", String.class)
        print(res)
    }

    def "高亮搜索"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"query\" : {\n" +
                "        \"match_phrase\" : {\n" +
                "            \"about\" : \"rock climbing\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"highlight\": {\n" +
                "        \"fields\" : {\n" +
                "            \"about\" : {}\n" +
                "        }\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }
}
