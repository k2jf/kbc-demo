package com.k2data.kbc.usrmgr.controller;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.usrmgr.model.Role;
import com.k2data.kbc.usrmgr.service.RoleService;
import com.k2data.kbc.usrmgr.service.request.CreateRoleRequest;
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

@Api("角色管理接口")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation("新增角色")
    @PostMapping
    public KbcResponse create(@RequestBody CreateRoleRequest createRoleRequest) {
        roleService.create(createRoleRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("添加用户组")
    @PostMapping("/{id}/roles/{usrgrpIds}")
    public KbcResponse addUserGroups(@PathVariable Integer id,
        @PathVariable String usrgrpIds) {

        roleService.addUserGroups(id, usrgrpIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新用户组")
    @PutMapping("/{id}/roles/{usrgrpIds}")
    public KbcResponse mergeUserGroups(@PathVariable Integer id,
        @PathVariable String usrgrpIds) {

        roleService.mergeUserGroups(id, usrgrpIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public KbcResponse delete(@PathVariable Integer id) throws KbcBizException {
        roleService.delete(id);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除用户组")
    @DeleteMapping(value = "/{id}/usrgrps/{usrgrpId}")
    public KbcResponse deleteUserGroup(@PathVariable Integer id,
        @PathVariable Integer usrgrpId) {

        roleService.deleteUserGroup(id, usrgrpId);
        return KbcResponse.SUCCESS;
    }

    //@ApiOperation("修改角色信息")
    //@PutMapping("/{id}")
    public KbcResponse modify(@PathVariable Integer id, @RequestBody Role role) {
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询角色列表")
    @GetMapping
    public KbcResponse list(@RequestParam(required = false) String fuzzyName,
        @RequestParam(required = false) Integer usrgrpId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("roles", roleService.list(fuzzyName, usrgrpId));
        return response;
    }
}
