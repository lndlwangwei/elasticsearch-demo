package cn.iwangwei.es.service.impl;

import cn.iwangwei.es.bean.Resource;
import cn.iwangwei.es.repository.ResourceRepository;
import cn.iwangwei.es.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<Resource> getAll() {
        List<Resource> result = new ArrayList<>();

        Iterable<Resource> resources = resourceRepository.findAll();
        resources.forEach(resource -> result.add(resource));

        return result;
    }

    @Override
    public List<Resource> getResourcesByName(String name) {
        return resourceRepository.getAllByTitle(name);
    }

    @Override
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }
}
