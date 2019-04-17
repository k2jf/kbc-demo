package com.k2data.kbc.usrmgr.service;

import com.k2data.kbc.usrmgr.dao.ResourceMapper;
import com.k2data.kbc.usrmgr.dao.RoleResourceMapper;
import com.k2data.kbc.usrmgr.dao.UserResourceMapper;
import com.k2data.kbc.usrmgr.dao.condition.ResourceCondition;
import com.k2data.kbc.usrmgr.model.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    UserResourceMapper userResourceMapper;

    @Autowired
    RoleResourceMapper roleResourceMapper;

    public List<Resource> list(Integer typeId, String fuzzyName) {
        ResourceCondition condition = new ResourceCondition();
        condition.setTypeId(typeId);
        condition.setFuzzyName(fuzzyName);
        return resourceMapper.list(condition);
    }
}
