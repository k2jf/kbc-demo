package com.k2data.kbc.usrmgr.service.request;

public class CreateUserRequest {

    private String name;

    private String email;

    private String password;

    private String usrgrpIds;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsrgrpIds() {
        return usrgrpIds;
    }

    public void setUsrgrpIds(String usrgrpIds) {
        this.usrgrpIds = usrgrpIds;
    }
}
