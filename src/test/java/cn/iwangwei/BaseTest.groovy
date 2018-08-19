package cn.iwangwei

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.client.RestTemplate
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class BaseTest extends Specification {

    @Autowired
    private WebApplicationContext wac
    @Shared
    protected MockMvc mockMvc

    protected static final String BASE_URL = "http://localhost:9200/";

    protected RestTemplate restTemplate = new RestTemplate();

    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }
}
