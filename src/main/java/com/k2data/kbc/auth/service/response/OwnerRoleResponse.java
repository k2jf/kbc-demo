package com.k2data.kbc.auth.service.response;


import com.k2data.kbc.auth.model.OwnerRole;
import com.k2data.kbc.auth.model.Role;

public class OwnerRoleResponse {

    private Integer roleId;
    private String roleName;
    private Integer ownerId;
    private Long effectTime;
    private Long expireTime;
    private Boolean disabled;

    public OwnerRoleResponse() {
    }

    public OwnerRoleResponse(OwnerRole ownerRole, Role role) {
        this.roleId = ownerRole.getRoleId();
        this.roleName = role.getName();
        this.ownerId = ownerRole.getOwnerId();
        if (null != ownerRole.getEffectTime()) {
            this.effectTime = ownerRole.getEffectTime().getTime();
        }

        if (null != ownerRole.getExpireTime()) {
            this.expireTime = ownerRole.getExpireTime().getTime();
        }
        this.disabled = ownerRole.getDisabled();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

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
}
