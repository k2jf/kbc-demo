package com.k2data.kbc.usrmgr.dao.condition;

import java.util.List;

public class UserResourceCondition {

    private Integer userId;

    private List<Integer> resourceIds;

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
