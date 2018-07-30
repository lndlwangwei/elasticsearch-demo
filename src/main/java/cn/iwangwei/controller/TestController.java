package cn.iwangwei.controller;

import cn.iwangwei.es.bean.Resource;
import cn.iwangwei.es.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ResourceService resourceService;

    @GetMapping("/test")
    public String hello() {
        return "hello world";
    }

    @PutMapping("/resources")
    public Resource add() {
        Resource resource = new Resource();
        resource.setId(1);
        resource.setTitle("test title");
        resource.setContent("test content");

        return resourceService.save(resource);
    }
}
