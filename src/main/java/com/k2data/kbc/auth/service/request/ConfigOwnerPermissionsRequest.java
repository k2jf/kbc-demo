package com.k2data.kbc.auth.service.request;

public class ConfigOwnerPermissionsRequest {

    private String operations;
    private Long effectTime;
    private Long expireTime;
    private Boolean disabled;

    public Long getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Long effectTime) {
        this.effectTime = effectTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
