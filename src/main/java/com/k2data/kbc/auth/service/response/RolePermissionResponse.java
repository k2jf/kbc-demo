package com.k2data.kbc.auth.service.response;

import com.k2data.kbc.auth.model.Resource;
import com.k2data.kbc.auth.model.RolePermission;

public class RolePermissionResponse {

    private Integer roleId;
    private Integer resourceId;
    private String resourceName;
    private String operations;

    public RolePermissionResponse() {
    }

    public RolePermissionResponse(RolePermission rolePermission, Resource resource) {
        this.roleId = rolePermission.getRoleId();
        this.resourceId = resource.getId();
        this.resourceName = resource.getName();
        this.operations = rolePermission.getOperations();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
