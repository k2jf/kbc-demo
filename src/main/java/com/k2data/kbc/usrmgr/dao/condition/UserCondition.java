package com.k2data.kbc.usrmgr.dao.condition;

import java.util.List;

public class UserCondition {

    private String fuzzyName;

    private List<Integer> ids;

    public String getFuzzyName() {
        return fuzzyName;
    }

    public void setFuzzyName(String fuzzyName) {
        this.fuzzyName = fuzzyName;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
