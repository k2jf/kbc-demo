package com.k2data.kbc.auth.service.response;

import com.k2data.kbc.auth.model.User;
import java.util.List;

public class UserDetailResponse {

    private Integer id;
    private String name;
    private String email;

    private List<PermissionResponse> permissions;

    public UserDetailResponse() {
    }

    public UserDetailResponse(User user, List<PermissionResponse> permissions) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.permissions = permissions;
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

    public List<PermissionResponse> getPermissions() {
        return permissions;
    }

    public void setPermissions(
        List<PermissionResponse> permissions) {
        this.permissions = permissions;
    }
}
