package com.k2data.kbc.usrmgr.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.usrmgr.service.UserService;
import com.k2data.kbc.usrmgr.service.request.CreateUserRequest;
import com.k2data.kbc.usrmgr.service.request.UpdateUserRequest;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("新增用户")
    @PostMapping
    public KbcResponse create(@RequestBody CreateUserRequest createUserRequest) {
        userService.create(createUserRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("新增用户权限关联")
    @PostMapping("/{id}/resusages")
    public KbcResponse addPermission(@PathVariable Integer id,
        @RequestParam Integer resusageId) {
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("添加用户组")
    @PostMapping("/{id}/usrgrps/{usrgrpIds}")
    public KbcResponse addUserGroups(@PathVariable Integer id,
        @PathVariable String usrgrpIds) {

        userService.addUserGroups(id, usrgrpIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新用户组")
    @PutMapping("/{id}/usrgrps/{usrgrpIds}")
    public KbcResponse mergeUserGroups(@PathVariable Integer id,
        @PathVariable String usrgrpIds) {

        userService.mergeUserGroups(id, usrgrpIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public KbcResponse delete(@PathVariable Integer id) {
        userService.delete(id);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户组")
    @DeleteMapping(value = "/{id}/usrgrps/{usrgrpId}")
    public KbcResponse deleteUserGroup(@PathVariable Integer id,
        @PathVariable Integer usrgrpId) {

        userService.deleteUserGroup(id, usrgrpId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/{id}")
    public KbcResponse modify(@PathVariable Integer id,
        @RequestBody UpdateUserRequest updateUserRequest) {
        userService.update(id, updateUserRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("修改密码")
    @PutMapping(value = "/{id}", params = "password")
    public KbcResponse modifyPwd(@PathVariable Integer id, @RequestParam String password) {
        userService.updatePwd(id, password);
        return KbcResponse.SUCCESS;
    }

    //@ApiOperation("分页查询用户列表")
    //@GetMapping(request = {"currentPage", "pageSize"})
    //public KbcResponse pageList(@RequestParam(defaultValue = "1") Integer currentPage,
    //    @RequestParam(defaultValue = "10") Integer pageSize) {
    //    KbcResponse response = new KbcResponse();
    //    return response;
    //}

    @ApiOperation("查询用户列表")
    @GetMapping
    public KbcResponse list(@RequestParam(required = false) String fuzzyName,
        @RequestParam(required = false) Integer usrgrpId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("users", userService.list(fuzzyName, usrgrpId));
        return response;
    }

    @ApiOperation("查询用户详情")
    @GetMapping("/{id}")
    public KbcResponse detail(@PathVariable Integer id) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("userDetail", userService.getById(id));
        return response;
    }
}
