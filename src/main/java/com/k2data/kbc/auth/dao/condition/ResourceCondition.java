package com.k2data.kbc.auth.dao.condition;

public class ResourceCondition {

    private Integer typeId;

    private String fuzzyName;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getFuzzyName() {
        return fuzzyName;
    }

    public void setFuzzyName(String fuzzyName) {
        this.fuzzyName = fuzzyName;
    }
}
