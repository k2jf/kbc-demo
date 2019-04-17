package com.k2data.kbc.usrmgr.service.response;

import com.k2data.kbc.usrmgr.model.User;
import java.util.List;

public class UserDetailResponse {

    private Integer id;
    private String name;
    private String email;
    private List<ResourceUsageResponse> resourceUsages;

    public UserDetailResponse() {
    }

    public UserDetailResponse(User user, List<ResourceUsageResponse> resourceUsages) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.resourceUsages = resourceUsages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ResourceUsageResponse> getResourceUsages() {
        return resourceUsages;
    }

    public void setResourceUsages(
        List<ResourceUsageResponse> resourceUsages) {
        this.resourceUsages = resourceUsages;
    }
}
