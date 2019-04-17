package com.k2data.kbc.usrmgr.service;

import com.k2data.kbc.usrmgr.dao.ResourceMapper;
import com.k2data.kbc.usrmgr.dao.RoleMapper;
import com.k2data.kbc.usrmgr.dao.RoleResourceMapper;
import com.k2data.kbc.usrmgr.dao.UserGroupMapper;
import com.k2data.kbc.usrmgr.dao.UserMapper;
import com.k2data.kbc.usrmgr.dao.UserResourceMapper;
import com.k2data.kbc.usrmgr.dao.UserUsrgrpMapper;
import com.k2data.kbc.usrmgr.dao.UsrgrpRoleMapper;
import com.k2data.kbc.usrmgr.dao.condition.UserCondition;
import com.k2data.kbc.usrmgr.model.Resource;
import com.k2data.kbc.usrmgr.model.RoleResource;
import com.k2data.kbc.usrmgr.model.User;
import com.k2data.kbc.usrmgr.model.UserGroup;
import com.k2data.kbc.usrmgr.model.UserResource;
import com.k2data.kbc.usrmgr.service.request.CreateUserRequest;
import com.k2data.kbc.usrmgr.service.request.UpdateUserRequest;
import com.k2data.kbc.usrmgr.service.response.ResourceUsageResponse;
import com.k2data.kbc.usrmgr.service.response.UserDetailResponse;
import com.k2data.kbc.usrmgr.service.response.UserResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserUsrgrpMapper userUsrgrpMapper;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    UserResourceMapper userResourceMapper;

    @Autowired
    UsrgrpRoleMapper usrgrpRoleMapper;

    @Autowired
    RoleResourceMapper roleResourceMapper;

    @Autowired
    ResourceMapper resourceMapper;

    @Transactional
    public void create(CreateUserRequest createUserRequest) {
        // 保存用户
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setPassword(createUserRequest.getPassword());
        user.setEmail(createUserRequest.getEmail());
        userMapper.insert(user);

        // 关联用户组
        String userGroupIdsStr = createUserRequest.getUsrgrpIds();
        if (StringUtils.isEmpty(userGroupIdsStr)) {
            return;
        }
        for (String userGroupIdStr : userGroupIdsStr.split(",")) {
            Integer userGroupId = Integer.valueOf(userGroupIdStr);
            UserGroup userGroup = userGroupMapper.getById(userGroupId);
            if (null != userGroup) {
                userUsrgrpMapper.insert(user.getId(), userGroupId);
            }
        }
    }

    @Transactional
    public void addUserGroups(Integer id, String usrgrpIds) {
        for (String usrgrpIdStr : usrgrpIds.split(",")) {
            userUsrgrpMapper.insert(id, Integer.valueOf(usrgrpIdStr));
        }
    }

    @Transactional
    public void mergeUserGroups(Integer id, String usrgrpIdsStr) {
        List<Integer> existUsrgrpIds = userUsrgrpMapper.getUsrgrpIdsByUserId(id);

        // 获取新关联
        for (String usrgrpIdStr : usrgrpIdsStr.split(",")) {
            Integer usrgrpId = Integer.valueOf(usrgrpIdStr);

            boolean existed = false;
            for (Integer existUsrgrpId : existUsrgrpIds) {
                if (usrgrpId == existUsrgrpId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                userUsrgrpMapper.insert(id, usrgrpId);
            }
        }

        // 获取已剔除
        for (Integer existUsrgrpId : existUsrgrpIds) {
            boolean existed = false;

            for (String usrgrpIdStr : usrgrpIdsStr.split(",")) {
                if (Integer.valueOf(usrgrpIdStr) == existUsrgrpId) {
                    existed = true;
                }
            }

            if (!existed) {
                userUsrgrpMapper.deleteByUserIdAndUsrgrpId(id, existUsrgrpId);
            }
        }
    }

    @Transactional
    public void delete(Integer id) {
        userUsrgrpMapper.deleteByUserId(id);
        userResourceMapper.deleteByUserId(id);
        userMapper.delete(id);
    }

    public void deleteUserGroup(Integer id, Integer usrgrpId) {
        userUsrgrpMapper.deleteByUserIdAndUsrgrpId(id, usrgrpId);
    }

    public void update(Integer id, UpdateUserRequest updateUserRequest) {
        User user = new User();
        user.setId(id);
        user.setName(updateUserRequest.getName());
        user.setEmail(updateUserRequest.getEmail());
        userMapper.update(user);
    }

    @Transactional
    public void updatePwd(Integer id, String pwd) {
        User user = new User();
        user.setId(id);
        user.setPassword(pwd);
        userMapper.update(user);
    }

    public List<UserResponse> list(String fuzzyName, Integer usrgrpId) {
        List<UserResponse> result = new ArrayList<>();

        UserCondition condition = new UserCondition();
        condition.setFuzzyName(fuzzyName);
        if (null != usrgrpId) {
            List<Integer> userIds  = userUsrgrpMapper.getUserIdsByUsrgrpId(usrgrpId);
            if (null == userIds || userIds.isEmpty()) {
                return result;
            }
            condition.setIds(userIds);
        }

        List<User> users = userMapper.list(condition);
        if (null == users) {
            return result;
        }

        for (User user : users) {
            result.add(new UserResponse(user));
        }
        return result;
    }

    public UserDetailResponse getById(Integer id) {
        User user = userMapper.getById(id);
        if (null == user) {
            return null;
        }

        List<UserResource> userResources = userResourceMapper.getByUserId(id);
        List<RoleResource> roleResources = null;
        List<Integer> usrgrpIds = userUsrgrpMapper.getUsrgrpIdsByUserId(id);
        if (null != usrgrpIds) {
            List<Integer> roleIds = usrgrpRoleMapper.getRoleIdsByUsrgrpIds(usrgrpIds);
            roleResources = roleResourceMapper.getByRoleIds(roleIds);
        }

        Map<Integer, String> resourceMap = resourceMap();
        List<ResourceUsageResponse> result = new ArrayList<>();
        if (null != userResources) {
            for (UserResource userResource : userResources) {
                result.add(new ResourceUsageResponse(userResource, resourceMap));
            }
        }
        if (null != roleResources) {
            for (RoleResource roleResource : roleResources) {
                result.add(new ResourceUsageResponse(roleResource, resourceMap));
            }
        }
        return new UserDetailResponse(user, result);
    }

    private Map<Integer, String> resourceMap() {
        Map<Integer, String> result = new HashMap<>();

        List<Resource> resources = resourceMapper.list(null);
        if (null == resources) {
            return result;
        }

        for (Resource resource : resources) {
            result.put(resource.getId(), resource.getName());
        }

        return result;
    }
}
