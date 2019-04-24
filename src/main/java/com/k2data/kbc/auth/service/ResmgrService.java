package com.k2data.kbc.auth.service;

import com.k2data.kbc.auth.dao.ResourceMapper;
import com.k2data.kbc.auth.dao.ResourceTypeMapper;
import com.k2data.kbc.auth.dao.condition.ResourceCondition;
import com.k2data.kbc.auth.model.Resource;
import com.k2data.kbc.auth.model.ResourceType;
import com.k2data.kbc.auth.service.response.ResourceTypeResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResmgrService {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    ResourceTypeMapper resourceTypeMapper;

    public List<ResourceTypeResponse> types() {
        List<ResourceTypeResponse> result = new ArrayList<>();
        List<ResourceType> resourceTypes = resourceTypeMapper.list();
        if (null == resourceTypes) {
            return result;
        }

        for (ResourceType resourceType : resourceTypes) {
            result.add(new ResourceTypeResponse(resourceType));
        }
        return result;
    }

    public List<Resource> list(Integer typeId, String fuzzyName) {
        ResourceCondition condition = new ResourceCondition();
        condition.setTypeId(typeId);
        condition.setFuzzyName(fuzzyName);
        return resourceMapper.list(condition);
    }

    public String getNameById(Integer id) {
        Resource resource = resourceMapper.getById(id);
        return null == resource ? null : resource.getName();
    }
}
