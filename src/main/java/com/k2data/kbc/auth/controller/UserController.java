package com.k2data.kbc.auth.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.service.UsrmgrService;
import com.k2data.kbc.auth.service.request.CreateUserRequest;
import com.k2data.kbc.auth.service.request.ModifyUserRequest;
import com.k2data.kbc.auth.service.request.RebindUserGroupsRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api("用户管理接口")
@RestController
@RequestMapping("/usrmgr/users")
public class UserController {

    @Autowired
    UsrmgrService usrmgrService;

    @ApiOperation("新增用户")
    @PostMapping
    public KbcResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        usrmgrService.createUser(createUserRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{userId}")
    public KbcResponse deleteUser(@PathVariable Integer userId) {
        usrmgrService.deleteUser(userId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("解除用户组绑定")
    @DeleteMapping(value = "/{userId}/usrgrps/{usrgrpId}")
    public KbcResponse unbindUserGroup(@PathVariable Integer userId,
        @PathVariable Integer usrgrpId) {
        usrmgrService.unbindUserGroup(userId, usrgrpId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/{userId}")
    public KbcResponse modifyUser(@PathVariable Integer userId,
        @RequestBody ModifyUserRequest modifyUserRequest) {
        usrmgrService.modifyUser(userId, modifyUserRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("修改密码")
    @PutMapping(value = "/{id}", params = "password")
    public KbcResponse modifyPwd(@PathVariable Integer userId, @RequestParam String password) {
        usrmgrService.modifyPwd(userId, password);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("修改用户所属的用户组")
    @PutMapping("/{userId}/usrgrps")
    public KbcResponse rebindUserGroups(@PathVariable Integer userId,
        @RequestBody RebindUserGroupsRequest rebindUserGroupsRequest) {
        usrmgrService.rebindUserGroups(userId, rebindUserGroupsRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询用户列表")
    @GetMapping
    public KbcResponse listUsers(@RequestParam(required = false) String fuzzyName,
        @RequestParam(required = false) Integer usrgrpId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("users", usrmgrService.listUsers(fuzzyName, usrgrpId));
        return response;
    }

    @ApiOperation("查询用户详情")
    @GetMapping("/{userId}")
    public KbcResponse detail(@PathVariable Integer userId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("userDetail", usrmgrService.getUserById(userId));
        return response;
    }
}
