package com.k2data.kbc.usrmgr.service;

import com.k2data.kbc.usrmgr.dao.ResourceTypeMapper;
import com.k2data.kbc.usrmgr.model.ResourceType;
import com.k2data.kbc.usrmgr.service.response.ResourceTypeResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceTypeService {

    @Autowired
    ResourceTypeMapper resourceTypeMapper;

    public List<ResourceTypeResponse> list() {
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

}
