package com.k2data.kbc.usrmgr.service;

import com.k2data.kbc.usrmgr.dao.ResourceMapper;
import com.k2data.kbc.usrmgr.dao.RoleResourceMapper;
import com.k2data.kbc.usrmgr.dao.UserResourceMapper;
import com.k2data.kbc.usrmgr.dao.condition.ResourceCondition;
import com.k2data.kbc.usrmgr.dao.condition.RoleResourceCondition;
import com.k2data.kbc.usrmgr.dao.condition.UserResourceCondition;
import com.k2data.kbc.usrmgr.model.Resource;
import com.k2data.kbc.usrmgr.model.RoleResource;
import com.k2data.kbc.usrmgr.model.UserResource;
import com.k2data.kbc.usrmgr.service.request.AuthorizeResourceRequest;
import com.k2data.kbc.usrmgr.service.response.ResourceUsageResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {

    @Autowired
    UserResourceMapper userResourceMapper;

    @Autowired
    RoleResourceMapper roleResourceMapper;

    @Autowired
    ResourceMapper resourceMapper;

    public void authorizeResource(AuthorizeResourceRequest authorizeResourceRequest) {
        if (null != authorizeResourceRequest.getUserId()) {
            UserResource userResource = new UserResource();
            userResource.setUserId(authorizeResourceRequest.getUserId());
            userResource.setResourceId(authorizeResourceRequest.getResourceId());
            userResource.setOperations(authorizeResourceRequest.getOperations());
            userResource.setEffectTime(new Date(authorizeResourceRequest.getEffectTime()));
            userResource.setExpireTime(new Date(authorizeResourceRequest.getExpireTime()));
            userResourceMapper.insert(userResource);
            return;
        }
        if (null != authorizeResourceRequest.getRoleId()) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(authorizeResourceRequest.getRoleId());
            roleResource.setResourceId(authorizeResourceRequest.getResourceId());
            roleResource.setOperations(authorizeResourceRequest.getOperations());
            roleResource.setEffectTime(new Date(authorizeResourceRequest.getEffectTime()));
            roleResource.setExpireTime(new Date(authorizeResourceRequest.getExpireTime()));
            roleResourceMapper.insert(roleResource);
        }
    }

    public void delete(Integer resurceId, Integer roleId, Integer userId) {
        if (null != roleId) {
            roleResourceMapper.delete(roleId, resurceId);
            return;
        }
        if (null != userId) {
            userResourceMapper.delete(userId, resurceId);
            return;
        }

    }

    public List<ResourceUsageResponse> list(Integer typeId, String fuzzyResourceName,
        Integer roleId, Integer userId) {
        List<ResourceUsageResponse> result = new ArrayList<>();
        ResourceCondition resourceCondition = new ResourceCondition();
        resourceCondition.setFuzzyName(fuzzyResourceName);
        resourceCondition.setTypeId(typeId);
        List<Resource> resources = resourceMapper.list(resourceCondition);

        Map<Integer, String> resourceMap = resourceMap(resources);

        if (null != userId || (null == userId && null == roleId)) {
            UserResourceCondition userResourceCondition = new UserResourceCondition();
            userResourceCondition.setResourceIds(getResourceIds(resources));
            userResourceCondition.setUserId(userId);
            List<UserResource> userResources = userResourceMapper.list(userResourceCondition);

            for (UserResource userResource : userResources) {
                result.add(new ResourceUsageResponse(userResource, resourceMap));
            }
        }
        if (null != roleId || (null == userId && null == roleId)) {
            RoleResourceCondition roleResourceCondition = new RoleResourceCondition();
            roleResourceCondition.setRoleId(roleId);
            List<RoleResource> roleResources = roleResourceMapper.list(roleResourceCondition);
            if (null != roleResources) {
                for (RoleResource roleResource : roleResources) {
                    result.add(new ResourceUsageResponse(roleResource, resourceMap));
                }
            }
        }

        return result;
    }

    private List<Integer> getResourceIds(List<Resource> resources) {
        List<Integer> result = new ArrayList<>();

        if (null == resources) {
            return result;
        }

        for (Resource resource : resources) {
            result.add(resource.getId());
        }
        return result;
    }

    private Map<Integer, String> resourceMap(List<Resource> resources) {
        Map<Integer, String> result = new HashMap<>();

        if (null == resources) {
            return result;
        }

        for (Resource resource : resources) {
            result.put(resource.getId(), resource.getName());
        }

        return result;
    }
}
