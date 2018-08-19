package cn.iwangwei.test

import cn.iwangwei.BaseTest

/**
 * 聚合查询
 */
class AggregationsRestTemplateGTests extends BaseTest {

    /**
     * 相当与group by
     * 备注：对text字段进行聚合会出现400错误，需要对mapping配置：interests字段：
     * PUT megacorp/_mapping/employee/
     *{*   "properties": {*     "interests": {*       "type":     "text",
     *       "fielddata": true
     *}*}*}* @return
     */
    def "按字段分组"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "  \"aggs\": {\n" +
                "    \"all_interests\": {\n" +
                "      \"terms\": { \"field\": \"interests\" }\n" +
                "    }\n" +
                "  }\n" +
                "}", String.class)
        print(res)
    }
/**
 * group by interests, age
 * @return
 */
    def "按字段进行多级分组"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"aggs\" : {\n" +
                "        \"all_interests\" : {\n" +
                "            \"terms\" : { \"field\" : \"interests\" },\n" +
                "            \"aggs\" : {\n" +
                "                \"all_age\" : {\n" +
                "                    \"terms\" : { \"field\" : \"age\" }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }

    def "查询age的平均值"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "    \"aggs\" : {\n" +
                "        \"avg_age\" : { " +
                "           \"avg\" : { \"field\" : \"age\" } }\n" +
                "    }\n" +
                "}", String.class)
        print(res)
    }

    def "带上筛选条件"() {
        expect:
        String res = restTemplate.postForObject(BASE_URL + "megacorp/employee/_search", "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"last_name\": \"smith\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"aggs\": {\n" +
                "    \"avg_age\": {\n" +
                "      \"avg\": {\n" +
                "        \"field\": \"age\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}", String.class)
        print(res)
    }
}
