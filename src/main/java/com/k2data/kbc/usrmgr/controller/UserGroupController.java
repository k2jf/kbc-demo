package com.k2data.kbc.usrmgr.controller;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.usrmgr.model.UserGroup;
import com.k2data.kbc.usrmgr.service.UserGroupService;
import com.k2data.kbc.usrmgr.service.request.CreateUserGroupRequest;
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
@RequestMapping("/usrgrps")
public class UserGroupController {

    @Autowired
    UserGroupService userGroupService;

    @ApiOperation("新增用户组")
    @PostMapping
    public KbcResponse create(@RequestBody CreateUserGroupRequest createUserGroupRequest) {
        userGroupService.create(createUserGroupRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("添加用户")
    @PostMapping("/{id}/users/{userIds}")
    public KbcResponse addUsers(@PathVariable Integer id,
        @PathVariable String userIds) {

        userGroupService.addUsers(id, userIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("添加角色")
    @PostMapping("/{id}/roles/{roleIds}")
    public KbcResponse addRoles(@PathVariable Integer id,
        @PathVariable String roleIds) {

        userGroupService.addRoles(id, roleIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新用户")
    @PutMapping("/{id}/users/{userIds}")
    public KbcResponse mergeUsers(@PathVariable Integer id,
        @PathVariable String userIds) {

        userGroupService.mergeUsers(id, userIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新角色")
    @PutMapping("/{id}/roles/{roleIds}")
    public KbcResponse mergeRoles(@PathVariable Integer id,
        @PathVariable String roleIds) {

        userGroupService.mergeRoles(id, roleIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户组")
    @DeleteMapping(value = "/{id}")
    public KbcResponse delete(@PathVariable Integer id) throws KbcBizException {

        userGroupService.delete(id);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{id}/users/{userId}")
    public KbcResponse deleteUser(@PathVariable Integer id,
        @PathVariable Integer userId) {

        userGroupService.deleteUser(id, userId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除角色")
    @DeleteMapping(value = "/{id}/roles/{roleId}")
    public KbcResponse deleteRole(@PathVariable Integer id,
        @PathVariable Integer roleId) {

        userGroupService.deleteRole(id, roleId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询用户组列表")
    @GetMapping
    public KbcResponse list(@RequestParam(required = false) String fuzzyName,
        @RequestParam(required = false) Integer userId,
        @RequestParam(required = false) Integer roleId) {

        KbcResponse response = new KbcResponse();
        List<UserGroup> userGroups = userGroupService.list(fuzzyName, userId, roleId);
        response.getBody().put("userGroups", userGroups);
        return response;
    }
}
