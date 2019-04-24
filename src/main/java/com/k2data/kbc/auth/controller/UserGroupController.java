package com.k2data.kbc.auth.controller;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.model.UserGroup;
import com.k2data.kbc.auth.service.UsrmgrService;
import com.k2data.kbc.auth.service.request.CreateUserGroupRequest;
import com.k2data.kbc.auth.service.request.ModifyUsersForUsrgrpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户组管理接口")
@RestController
@RequestMapping("/usrmgr/usrgrps")
public class UserGroupController {

    @Autowired
    UsrmgrService usrmgrService;

    @ApiOperation("新增用户组")
    @PostMapping
    public KbcResponse createUserGroup(@RequestBody CreateUserGroupRequest createUserGroupRequest) {
        usrmgrService.createUserGroup(createUserGroupRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户组")
    @DeleteMapping("/{id}")
    public KbcResponse deleteUserGroup(@PathVariable Integer id) throws KbcBizException {
        usrmgrService.deleteUserGroup(id);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户组下的用户")
    @DeleteMapping(value = "/{id}/users/{userId}")
    public KbcResponse deleteUserFromUsrgrp(@PathVariable Integer id,
        @PathVariable Integer userId) {
        usrmgrService.deleteUserFromUsrgrp(id, userId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新用户组下的用户")
    @PutMapping("/{id}/users")
    public KbcResponse modifyUsersForUsrgrp(@PathVariable Integer id,
        @RequestBody ModifyUsersForUsrgrpRequest modifyUsersForUsrgrpRequest) {
        usrmgrService.modifyUsersForUsrgrp(id, modifyUsersForUsrgrpRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询用户组列表")
    @GetMapping
    public KbcResponse listUserGroups(@RequestParam(required = false) String fuzzyName,
        @RequestParam(required = false) Integer userId,
        @RequestParam(required = false) Integer roleId) {
        KbcResponse response = new KbcResponse();
        List<UserGroup> userGroups = usrmgrService.listUserGroups(fuzzyName, userId, roleId);
        response.getBody().put("userGroups", userGroups);
        return response;
    }
}
