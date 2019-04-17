package com.k2data.kbc.usrmgr.service.response;

import com.k2data.kbc.usrmgr.model.ResourceUsage;
import java.util.Map;

public class ResourceUsageResponse {

    private String resource;
    private String operations;
    private Long effectTime;
    private Long expireTime;
    private boolean disabled;

    public ResourceUsageResponse() {
    }

    public ResourceUsageResponse(ResourceUsage resourceUsage, Map<Integer, String> resourceMap) {
        this.resource = resourceMap.get(resourceUsage.getResourceId());
        this.operations = resourceUsage.getOperations();
        this.effectTime =
            null != resourceUsage.getEffectTime() ? resourceUsage.getEffectTime().getTime() : null;
        this.expireTime =
            null != resourceUsage.getExpireTime() ? resourceUsage.getExpireTime().getTime() : null;
        this.disabled = resourceUsage.isDisabled();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }
}
