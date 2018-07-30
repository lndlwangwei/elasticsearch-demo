package cn.iwangwei.es.service;

import cn.iwangwei.es.bean.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> getAll();

    List<Resource> getResourcesByName(String name);

    Resource save(Resource resource);
}
