package com.k2data.kbc.auth.controller.permmgr;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.service.PermmgrSevice;
import com.k2data.kbc.auth.service.request.CreateRoleRequest;
import com.k2data.kbc.auth.service.request.ModifyPermissionRequest;
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

@Api("权限管理接口-角色")
@RestController
@RequestMapping("/permmgr/roles")
public class RoleController {

    @Autowired
    PermmgrSevice permmgrSevice;

    @ApiOperation("新增角色")
    @PostMapping
    public KbcResponse createRole(@RequestBody CreateRoleRequest createRoleRequest) {
        permmgrSevice.createRole(createRoleRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{roleId}")
    public KbcResponse deleteRole(@PathVariable Integer roleId) throws KbcBizException {
        permmgrSevice.deleteRole(roleId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除角色关联的权限")
    @DeleteMapping("/{roleId}/permissions")
    public KbcResponse deleteRolePermission(@PathVariable Integer roleId,
        @RequestParam String resourceIds) {
        permmgrSevice.deleteRolePermissions(roleId, resourceIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新角色下的拥有者")
    @PutMapping("/{roleId}/rowners")
    public KbcResponse modifyOwnersForRole(@PathVariable Integer roleId,
        @RequestParam String rownerIds) {

        permmgrSevice.modifyOwnersForRole(roleId, rownerIds);
        return KbcResponse.SUCCESS;
    }


    @ApiOperation("更新角色下的权限")
    @PutMapping("/{roleId}/permissions")
    public KbcResponse modifyRolePermssions(@PathVariable Integer roleId,
        @RequestBody ModifyPermissionRequest modifyPermissionRequest) {

        permmgrSevice.modifyRolePermssions(roleId, modifyPermissionRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("配置角色下的权限")
    @PutMapping(value = "/{roleId}/permissions", params = "action=config")
    public KbcResponse configRolePermssions(@PathVariable Integer roleId,
        @RequestParam String resourceIds,
        @RequestParam String operations) {
        permmgrSevice.configRolePermssions(roleId, resourceIds, operations);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询角色列表")
    @GetMapping
    public KbcResponse listRoles(@RequestParam(required = false) String fuzzyName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("roles", permmgrSevice.listRoles(fuzzyName));
        return response;
    }

    @ApiOperation("查询角色下权限信息")
    @GetMapping("/{roleId}/permissions")
    public KbcResponse listRolePermissions(@PathVariable Integer roleId,
        @RequestParam Integer resourceTypeId,
        @RequestParam(required = false) String fuzzyResName) {
        KbcResponse response = new KbcResponse();
        response.getBody()
            .put("roles", permmgrSevice.listRolePermissions(roleId, resourceTypeId, fuzzyResName));
        return response;
    }
}
