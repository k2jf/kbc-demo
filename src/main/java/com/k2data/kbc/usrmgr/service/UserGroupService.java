package com.k2data.kbc.usrmgr.service;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.usrmgr.dao.UserGroupMapper;
import com.k2data.kbc.usrmgr.dao.UserUsrgrpMapper;
import com.k2data.kbc.usrmgr.dao.UsrgrpRoleMapper;
import com.k2data.kbc.usrmgr.dao.condition.UserGroupCondition;
import com.k2data.kbc.usrmgr.model.UserGroup;
import com.k2data.kbc.usrmgr.service.request.CreateUserGroupRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserGroupService {

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    UserUsrgrpMapper userUsrgrpMapper;

    @Autowired
    UsrgrpRoleMapper usrgrpRoleMapper;

    public void create(CreateUserGroupRequest createUserGroupRequest) {
        if (null == createUserGroupRequest || StringUtils
            .isEmpty(createUserGroupRequest.getName())) {
            return;
        }
        UserGroup userGroup = new UserGroup();
        userGroup.setName(createUserGroupRequest.getName());
        userGroupMapper.insert(userGroup);
    }

    @Transactional
    public void addUsers(Integer id, String userIds) {
        for (String userIdStr : userIds.split(",")) {
            userUsrgrpMapper.insert(Integer.valueOf(userIdStr), id);
        }
    }

    @Transactional
    public void mergeUsers(Integer id, String userIdsStr) {
        List<Integer> existUserIds = userUsrgrpMapper.getUserIdsByUsrgrpId(id);

        // 获取新关联的userId
        for (String userIdStr : userIdsStr.split(",")) {
            Integer userId = Integer.valueOf(userIdStr);

            boolean existed = false;
            for (Integer existUserId : existUserIds) {
                if (userId == existUserId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                userUsrgrpMapper.insert(userId, id);
            }
        }

        // 获取已剔除的userId
        for (Integer existUserId : existUserIds) {
            boolean existed = false;

            for (String userIdStr : userIdsStr.split(",")) {
                if (Integer.valueOf(userIdStr) == existUserId) {
                    existed = true;
                }
            }

            if (!existed) {
                userUsrgrpMapper.deleteByUserIdAndUsrgrpId(existUserId, id);
            }
        }
    }

    @Transactional
    public void addRoles(Integer id, String roleIds) {
        for (String roleIdStr : roleIds.split(",")) {
            usrgrpRoleMapper.insert(Integer.valueOf(roleIdStr), id);
        }
    }

    @Transactional
    public void mergeRoles(Integer id, String roleIdStrs) {
        List<Integer> existRoleIds = usrgrpRoleMapper.getRoleIdsByUsrgrpId(id);

        // 获取新关联的roleId
        for (String roleIdStr : roleIdStrs.split(",")) {
            Integer roleId = Integer.valueOf(roleIdStr);

            boolean existed = false;
            for (Integer existRoleId : existRoleIds) {
                if (roleId == existRoleId) {
                    existed = true;
                    break;
                }
            }

            if (!existed) {
                usrgrpRoleMapper.insert(roleId, id);
            }
        }

        // 获取已剔除的userId
        for (Integer existRoleId : existRoleIds) {
            boolean existed = false;

            for (String roleIdStr : roleIdStrs.split(",")) {
                if (Integer.valueOf(roleIdStr) == existRoleId) {
                    existed = true;
                }
            }

            if (!existed) {
                usrgrpRoleMapper.deleteByUsrgrpIdAndRoleId(id, existRoleId);
            }
        }
    }

    public void delete(Integer id) throws KbcBizException {
        List<Integer> userIds = userUsrgrpMapper.getUserIdsByUsrgrpId(id);
        if (null != userIds && !userIds.isEmpty()) {
            throw new KbcBizException("用户组下尚有用户存在，不允许删除！");
        }
        userGroupMapper.delete(id);
    }

    public void deleteUser(Integer id, Integer userId) {
        userUsrgrpMapper.deleteByUserIdAndUsrgrpId(userId, id);
    }

    public void deleteRole(Integer id, Integer roleId) {
        usrgrpRoleMapper.deleteByUsrgrpIdAndRoleId(id, roleId);
    }

    public List<UserGroup> list(String fuzzyName, Integer userId, Integer roleId) {
        UserGroupCondition condition = new UserGroupCondition();
        condition.setFuzzyName(fuzzyName);

        if (null != userId) {
            List<Integer> ids = userUsrgrpMapper.getUsrgrpIdsByUserId(userId);
            if (null == ids || ids.isEmpty()) {
                return new ArrayList<>();
            }
            condition.setIds(ids);
        } else if (null != roleId) {
            List<Integer> ids = usrgrpRoleMapper.getUsrgrpIdsByRoleId(roleId);
            if (null == ids || ids.isEmpty()) {
                return new ArrayList<>();
            }
            condition.setIds(ids);
        }

        return userGroupMapper.list(condition);
    }
}
