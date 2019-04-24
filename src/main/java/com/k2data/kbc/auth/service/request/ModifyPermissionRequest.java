package com.k2data.kbc.auth.service.request;

public class ModifyPermissionRequest {

    private String resourceIds;

    private String operations;

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
