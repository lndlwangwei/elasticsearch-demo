package cn.iwangwei.test

import cn.iwangwei.BaseTest

class IndexRestTemplateGTests extends BaseTest {

/**
 * 如果index和type不存在，es会自动创建一个
 */
    def "指定id，添加一个文档"() {
        expect:
        restTemplate.put(BASE_URL + "twitter/doc/99", "{\n" +
                "    \"user\" : \"kimchy\",\n" +
                "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                "    \"message\" : \"trying out Elasticsearch\"\n" +
                "}")
    }
/**
 * 多线程环境下，确保在更新时，此文档没有被其他线程更新
 * 只有当前文档的version与请求指定version一致，才能更新成功
 * 备注：每次put操作，是会version加1
 */
    def "添加一个文档，指定version"() {
        expect:
        restTemplate.put(BASE_URL + "twitter/doc/98?version=3", "{\n" +
                "    \"user\" : \"kimchy\",\n" +
                "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                "    \"message\" : \"trying out Elasticsearch -version2\"\n" +
                "}")
    }

    def "添加一个文档，自动生成id"() {
        expect:
        String response = restTemplate.postForObject(BASE_URL + "twitter/doc", "{\n" +
                "    \"user\" : \"kimchy\",\n" +
                "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                "    \"message\" : \"trying out Elasticsearch -version2\"\n" +
                "}", String.class)

        print(response)
    }

//    def "添加一个文档，指定route"() {
//        expect:
//        restTemplate.put(BASE_URL + "twitter/doc/97?routing=wangwei1", "{\n" +
//                "    \"user\" : \"kimchy\",\n" +
//                "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
//                "    \"message\" : \"trying out Elasticsearch\"\n" +
//                "}")
//    }

    /**
     * 下面的是学习过程中需要使用的例子
     */
    def "index example 1"() {
        expect:
        restTemplate.put(BASE_URL + "/megacorp/employee/1", "{\n" +
                "    \"first_name\" : \"John\",\n" +
                "    \"last_name\" :  \"Smith\",\n" +
                "    \"age\" :        25,\n" +
                "    \"about\" :      \"I love to go rock climbing\",\n" +
                "    \"interests\": [ \"sports\", \"music\" ]\n" +
                "}")
    }

    def "index example 2"() {
        expect:
        restTemplate.put(BASE_URL + "/megacorp/employee/2", "{\n" +
                "    \"first_name\" :  \"Jane\",\n" +
                "    \"last_name\" :   \"Smith\",\n" +
                "    \"age\" :         32,\n" +
                "    \"about\" :       \"I like to collect rock albums\",\n" +
                "    \"interests\":  [ \"music\" ]\n" +
                "}")
    }

    def "index example 3"() {
        expect:
        restTemplate.put(BASE_URL + "/megacorp/employee/3", "{\n" +
                "    \"first_name\" :  \"Douglas\",\n" +
                "    \"last_name\" :   \"Fir\",\n" +
                "    \"age\" :         35,\n" +
                "    \"about\":        \"I like to build cabinets\",\n" +
                "    \"interests\":  [ \"forestry\" ]\n" +
                "}")
    }
}
