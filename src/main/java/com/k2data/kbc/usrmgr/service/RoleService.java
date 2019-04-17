package com.k2data.kbc.usrmgr.service;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.usrmgr.dao.RoleMapper;
import com.k2data.kbc.usrmgr.dao.UsrgrpRoleMapper;
import com.k2data.kbc.usrmgr.dao.condition.RoleCondition;
import com.k2data.kbc.usrmgr.model.Role;
import com.k2data.kbc.usrmgr.service.request.CreateRoleRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UsrgrpRoleMapper usrgrpRoleMapper;

    public void create(CreateRoleRequest createRoleRequest) {
        Role role = new Role();
        role.setName(createRoleRequest.getName());
        role.setDescription(createRoleRequest.getDescription());

        roleMapper.insert(role);
    }

    @Transactional
    public void addUserGroups(Integer id, String usrgrpIds) {
        for (String usrgrpIdStr : usrgrpIds.split(",")) {
            usrgrpRoleMapper.insert(id, Integer.valueOf(usrgrpIdStr));
        }
    }

    @Transactional
    public void mergeUserGroups(Integer id, String usrgrpIdsStr) {
        List<Integer> existUsrgrpIds = usrgrpRoleMapper.getUsrgrpIdsByRoleId(id);

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
                usrgrpRoleMapper.insert(id, usrgrpId);
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
                usrgrpRoleMapper.deleteByUsrgrpIdAndRoleId(existUsrgrpId, id);
            }
        }
    }

    public void delete(Integer id) throws KbcBizException {
        List<Integer> roleIds = usrgrpRoleMapper.getUsrgrpIdsByRoleId(id);
        if (null != roleIds && !roleIds.isEmpty()) {
            throw new KbcBizException("有关联此角色的用户组，不能删除");
        }

        roleMapper.delete(id);
    }

    public void deleteUserGroup(Integer id, Integer usrgrpId) {
        usrgrpRoleMapper.deleteByUsrgrpIdAndRoleId(usrgrpId, id);
    }

    public List<Role> list(String fuzzyName, Integer usrgrpId) {

        RoleCondition condition = new RoleCondition();
        condition.setFuzzyName(fuzzyName);
        if (null != usrgrpId) {
            List<Integer> roleIds = usrgrpRoleMapper.getRoleIdsByUsrgrpId(usrgrpId);
            if (null == roleIds || roleIds.isEmpty()) {
                return new ArrayList<>();
            }
            condition.setIds(roleIds);
        }

        return roleMapper.list(condition);
    }
}
