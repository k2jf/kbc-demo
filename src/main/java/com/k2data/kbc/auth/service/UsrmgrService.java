package com.k2data.kbc.auth.service;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.auth.dao.UserGroupMapper;
import com.k2data.kbc.auth.dao.UserMapper;
import com.k2data.kbc.auth.dao.UserUsrgrpMapper;
import com.k2data.kbc.auth.dao.condition.UserCondition;
import com.k2data.kbc.auth.dao.condition.UserGroupCondition;
import com.k2data.kbc.auth.model.User;
import com.k2data.kbc.auth.model.UserGroup;
import com.k2data.kbc.auth.service.request.CreateUserGroupRequest;
import com.k2data.kbc.auth.service.request.CreateUserRequest;
import com.k2data.kbc.auth.service.request.ModifyUserRequest;
import com.k2data.kbc.auth.service.request.ModifyUsersForUsrgrpRequest;
import com.k2data.kbc.auth.service.request.RebindUserGroupsRequest;
import com.k2data.kbc.auth.service.response.PermissionResponse;
import com.k2data.kbc.auth.service.response.UserDetailResponse;
import com.k2data.kbc.auth.service.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UsrmgrService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserUsrgrpMapper userUsrgrpMapper;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    PermmgrSevice permmgrSevice;

    @Transactional
    public void createUser(CreateUserRequest createUserRequest) throws KbcBizException {
        // 校验
        String name = createUserRequest.getName();
        if (StringUtils.isEmpty(name)) {
            throw new KbcBizException("用户名称不能为空！");
        }
        User existedUser = userMapper.getByName(name);
        if (null != existedUser) {
            throw new KbcBizException("用户名称已经存在！");
        }
        String password = createUserRequest.getPassword();
        if (StringUtils.isEmpty(password)) {
            throw new KbcBizException("密码不能为空！");
        }
        String email = createUserRequest.getEmail();
        if (!isEmail(email)) {
            throw new KbcBizException("邮箱不合法！");
        }

        // 保存用户
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
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

    public boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return true;
        }

        return false;
    }


    @Transactional
    public void deleteUser(Integer userId) {
        permmgrSevice.deletePermissionsByOwnerId(userId);
        List<Integer> usrgrpIds = userUsrgrpMapper.getUsrgrpIdsByUserId(userId);
        for (Integer usrgrpId : usrgrpIds) {
            permmgrSevice.deleteRolesByOwnerId(usrgrpId);
        }

        userUsrgrpMapper.deleteByUserId(userId);
        userMapper.delete(userId);
    }

    public void unbindUserGroup(Integer userId, Integer usrgrpId) {
        userUsrgrpMapper.deleteByUserIdAndUsrgrpId(userId, usrgrpId);
    }

    public void modifyUser(Integer userId, ModifyUserRequest modifyUserRequest) {
        User user = new User();
        user.setId(userId);
        user.setName(modifyUserRequest.getName());
        user.setEmail(modifyUserRequest.getEmail());
        userMapper.update(user);
    }

    public void modifyPwd(Integer userId, String pwd) {
        User user = new User();
        user.setId(userId);
        user.setPassword(pwd);
        userMapper.update(user);
    }

    public void rebindUserGroups(Integer userId, RebindUserGroupsRequest rebindUserGroupsRequest) {
        String usrgrpIdsStr = rebindUserGroupsRequest.getUsrgrpIds();
        String[] usrgrpIds = null == usrgrpIdsStr ? new String[0] : usrgrpIdsStr.split(",");

        List<Integer> existUsrgrpIds = userUsrgrpMapper.getUsrgrpIdsByUserId(userId);

        // 获取新关联
        for (String usrgrpIdStr : usrgrpIds) {
            Integer usrgrpId = Integer.valueOf(usrgrpIdStr);

            boolean existed = false;
            for (Integer existUsrgrpId : existUsrgrpIds) {
                if (usrgrpId == existUsrgrpId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                userUsrgrpMapper.insert(userId, usrgrpId);
            }
        }

        // 获取已剔除
        for (Integer existUsrgrpId : existUsrgrpIds) {
            boolean existed = false;

            for (String usrgrpIdStr : usrgrpIds) {
                if (Integer.valueOf(usrgrpIdStr) == existUsrgrpId) {
                    existed = true;
                }
            }

            if (!existed) {
                userUsrgrpMapper.deleteByUserIdAndUsrgrpId(userId, existUsrgrpId);
            }
        }
    }

    public List<UserResponse> listUsers(String fuzzyName, Integer usrgrpId) {
        List<UserResponse> result = new ArrayList<>();

        UserCondition condition = new UserCondition();
        condition.setFuzzyName(fuzzyName);
        if (null != usrgrpId) {
            List<Integer> userIds = userUsrgrpMapper.getUserIdsByUsrgrpId(usrgrpId);
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

    public UserDetailResponse getUserById(Integer userId) {
        User user = userMapper.getById(userId);
        if (null == user) {
            return null;
        }

        List<Integer> usrgrpIds = userUsrgrpMapper.getUsrgrpIdsByUserId(userId);
        List<PermissionResponse> permissionResponses = permmgrSevice
            .getPermissions(userId, usrgrpIds);
        return new UserDetailResponse(user, permissionResponses);
    }

    public boolean validateUserPassword(String name, String password) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }
        User user = userMapper.getByName(name);
        if (null == user) {
            return false;
        }

        return password.equals(user.getPassword());
    }

    // =========================================================
    public void createUserGroup(CreateUserGroupRequest createUserGroupRequest) {
        if (null == createUserGroupRequest || StringUtils
            .isEmpty(createUserGroupRequest.getName())) {
            return;
        }
        UserGroup userGroup = new UserGroup();
        userGroup.setName(createUserGroupRequest.getName());
        userGroupMapper.insert(userGroup);
    }

    public void deleteUserGroup(Integer usrgrpId) throws KbcBizException {
        List<Integer> userIds = userUsrgrpMapper.getUserIdsByUsrgrpId(usrgrpId);
        if (null != userIds && !userIds.isEmpty()) {
            throw new KbcBizException("用户组下尚有用户存在，不允许删除！");
        }
        userGroupMapper.delete(usrgrpId);
    }

    public void deleteUserFromUsrgrp(Integer usrgrpId, Integer userId) {
        userUsrgrpMapper.deleteByUserIdAndUsrgrpId(userId, usrgrpId);
    }

    @Transactional
    public void modifyUsersForUsrgrp(Integer usrgrpId,
        ModifyUsersForUsrgrpRequest modifyUsersForUsrgrpRequest) {
        String userIdsStr = modifyUsersForUsrgrpRequest.getUserIds();
        String[] userIdStrs = null == userIdsStr ? new String[0] : userIdsStr.split(",");
        List<Integer> existUserIds = userUsrgrpMapper.getUserIdsByUsrgrpId(usrgrpId);

        // 获取新关联的userId
        for (String userIdStr : userIdStrs) {
            Integer userId = Integer.valueOf(userIdStr);

            boolean existed = false;
            for (Integer existUserId : existUserIds) {
                if (userId == existUserId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                userUsrgrpMapper.insert(userId, usrgrpId);
            }
        }

        // 获取已剔除的userId
        for (Integer existUserId : existUserIds) {
            boolean existed = false;

            for (String userIdStr : userIdStrs) {
                if (Integer.valueOf(userIdStr) == existUserId) {
                    existed = true;
                }
            }

            if (!existed) {
                userUsrgrpMapper.deleteByUserIdAndUsrgrpId(existUserId, usrgrpId);
            }
        }
    }

    public List<UserGroup> listUserGroups(String fuzzyName, Integer userId, Integer roleId) {
        UserGroupCondition condition = new UserGroupCondition();
        condition.setFuzzyName(fuzzyName);

        if (null != userId) {
            List<Integer> ids = userUsrgrpMapper.getUsrgrpIdsByUserId(userId);
            if (null == ids || ids.isEmpty()) {
                return new ArrayList<>();
            }
            condition.setIds(ids);
        } else if (null != roleId) {
            List<Integer> ids = permmgrSevice.getRoleOwnerIds(roleId);
            if (null == ids || ids.isEmpty()) {
                return new ArrayList<>();
            }
            condition.setIds(ids);
        }

        return userGroupMapper.list(condition);
    }
}
