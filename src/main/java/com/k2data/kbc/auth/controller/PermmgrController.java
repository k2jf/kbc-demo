package com.k2data.kbc.auth.controller;

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

@Api("权限管理接口")
@RestController
@RequestMapping("/permmgr")
public class PermmgrController {

    @Autowired
    PermmgrSevice permmgrSevice;

    @ApiOperation("新增角色")
    @PostMapping("/roles")
    public KbcResponse createRole(@RequestBody CreateRoleRequest createRoleRequest) {
        permmgrSevice.createRole(createRoleRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/roles/{roleId}")
    public KbcResponse deleteRole(@PathVariable Integer roleId) throws KbcBizException {
        permmgrSevice.deleteRole(roleId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除拥有者关联的角色")
    @DeleteMapping("/rowners/{roleOwnerId}/roles/{roleId}")
    public KbcResponse deleteOwnerRole(@PathVariable Integer roleOwnerId,
        @PathVariable Integer roleId) {
        permmgrSevice.deleteOwnerRole(roleOwnerId, roleId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除拥有者关联的权限")
    @DeleteMapping("/owners/{ownerId}/permissions/{resourceId}")
    public KbcResponse deleteOwnerPermission(@PathVariable Integer ownerId,
        @PathVariable Integer resourceId) {
        permmgrSevice.deleteOwnerPermission(ownerId, resourceId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除角色关联的权限")
    @DeleteMapping("/roles/{roleId}/permissions/{resourceId}")
    public KbcResponse deleteRolePermission(@PathVariable Integer roleId,
        @PathVariable Integer resourceId) {
        permmgrSevice.deleteRolePermission(roleId, resourceId);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新拥有者下的权限")
    @PutMapping("/owners/{ownerId}/permissions")
    public KbcResponse modifyOwnerPermissions(@PathVariable Integer ownerId,
        @RequestBody ModifyPermissionRequest modifyPermissionRequest) {
        permmgrSevice.modifyOwnerPermissions(ownerId, modifyPermissionRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新拥有者的角色")
    @PutMapping(value = "/rowners/{roleOwnerId}/roles", params = "roleIds")
    public KbcResponse modifyOwnerRoles(@PathVariable Integer roleOwnerId,
        @RequestParam String roleIds) {
        permmgrSevice.modifyOwnerRoles(roleOwnerId, roleIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新角色下的拥有者")
    @PutMapping("/roles/{roleId}/rowners")
    public KbcResponse modifyOwnersForRole(@PathVariable Integer roleId,
        @RequestParam String rownerIds) {

        permmgrSevice.modifyOwnersForRole(roleId, rownerIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新角色下的权限")
    @PutMapping("/roles/{roleId}/permissions")
    public KbcResponse modifyRolePermssions(@PathVariable Integer roleId,
        @RequestParam(required = false) String resourceIds, @RequestParam(required = false) String operations) {

        permmgrSevice.modifyRolePermssions(roleId, resourceIds, operations);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("设置拥有者角色是否可用")
    @PutMapping(value = "/rowners/{roleOwnerId}/roles/{roleId}")
    public KbcResponse availableOwnerRole(@PathVariable Integer roleOwnerId,
        @PathVariable Integer roleId,
        @RequestParam(required = false) Long effectTime,
        @RequestParam(required = false) Long expireTime,
        @RequestParam(required = false) Boolean disabled) {
        permmgrSevice.availableOwnerRole(roleOwnerId, roleId, effectTime, expireTime, disabled);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("设置拥有者权限是否可用")
    @PutMapping(value = "/owners/{ownerId}/permissions/{resourceId}")
    public KbcResponse availableOwnerPermission(@PathVariable Integer ownerId,
        @PathVariable Integer resourceId,
        @RequestParam(required = false) Long effectTime,
        @RequestParam(required = false) Long expireTime,
        @RequestParam(required = false) Boolean disabled) {
        permmgrSevice.availableOwnerPermission(ownerId, resourceId, effectTime, expireTime, disabled);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询角色列表")
    @GetMapping("/roles")
    public KbcResponse listRoles(@RequestParam(required = false) String fuzzyName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("roles", permmgrSevice.listRoles(fuzzyName));
        return response;
    }

    @ApiOperation("查询角色下权限信息")
    @GetMapping("/roles/{roleId}/permissions")
    public KbcResponse listRolePermissions(@PathVariable Integer roleId,
        @RequestParam Integer resourceTypeId,
        @RequestParam(required = false) String fuzzyResName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("roles", permmgrSevice.listRolePermissions(roleId, resourceTypeId, fuzzyResName));
        return response;
    }

    @ApiOperation("查询拥有者下的角色列表")
    @GetMapping("/rowners/{rownerId}/roles")
    public KbcResponse listOwnerRoles(@RequestParam(required = false) String fuzzyName,
        @PathVariable Integer rownerId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("ownerRoles", permmgrSevice.listOwnerRoles(fuzzyName, rownerId));
        return response;
    }

    @ApiOperation("查询拥有者下的权限列表")
    @GetMapping("/owners/{ownerId}/permissions")
    public KbcResponse listOwnerPermissions(@PathVariable Integer ownerId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("permissions", permmgrSevice.getPermissions(ownerId, null));
        return response;
    }

    @ApiOperation("查询拥有者下的权限列表（不包含关联角色下的权限）")
    @GetMapping(value = "/owners/{ownerId}/permissions", params = "resourceTypeId")
    public KbcResponse listOwnerdDirectPermissions(@PathVariable Integer ownerId, @RequestParam Integer resourceTypeId, @RequestParam(required = false) String fuzzyResName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("permissions", permmgrSevice.getPermissionsByPermOwnerId(ownerId, resourceTypeId, fuzzyResName));
        return response;
    }
}
