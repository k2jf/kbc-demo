package com.k2data.kbc.usrmgr.dao.condition;

import java.util.List;

public class RoleResourceCondition {

    private Integer roleId;

    private List<Integer> resourceIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
