package com.k2data.kbc.usrmgr.dao.condition;

import java.util.List;

public class PermissionCondition {

    private List<Integer> ids;

    private List<Integer> resourceIds;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
