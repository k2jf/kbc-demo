package com.k2data.kbc.auth.service.response;

import com.k2data.kbc.auth.model.User;

public class UserResponse {

    private Integer id;

    private String name;

    private String email;

    public UserResponse() {
    }

    public UserResponse(User user) {
        if (null == user) {
            return;
        }

        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
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
}
