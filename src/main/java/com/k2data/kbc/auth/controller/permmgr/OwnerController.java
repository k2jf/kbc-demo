package com.k2data.kbc.auth.controller.permmgr;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.service.PermmgrSevice;
import com.k2data.kbc.auth.service.request.ConfigOwnerPermissionsRequest;
import com.k2data.kbc.auth.service.request.ModifyPermissionRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("权限管理接口-权限用户关联")
@RestController
@RequestMapping("/permmgr/owners")
public class OwnerController {

    @Autowired
    PermmgrSevice permmgrSevice;

    @ApiOperation("删除拥有者关联的权限")
    @DeleteMapping("/{ownerId}/permissions")
    public KbcResponse deleteOwnerPermissions(@PathVariable Integer ownerId,
        @RequestParam String resourceIds) {
        permmgrSevice.deleteOwnerPermissions(ownerId, resourceIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新拥有者下的权限")
    @PutMapping("/{ownerId}/permissions")
    public KbcResponse modifyOwnerPermissions(@PathVariable Integer ownerId,
        @RequestBody ModifyPermissionRequest modifyPermissionRequest) {
        permmgrSevice.modifyOwnerPermissions(ownerId, modifyPermissionRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("配置拥有者的权限")
    @PutMapping(value = "/{ownerId}/permissions", params = "action=config")
    public KbcResponse configOwnerPermissions(@PathVariable Integer ownerId,
        @RequestParam String resourceIds,
        @RequestBody ConfigOwnerPermissionsRequest configOwnerPermissionsRequest) {
        permmgrSevice
            .configOwnerPermissions(ownerId, resourceIds, configOwnerPermissionsRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询拥有者下的权限列表")
    @GetMapping("/{ownerId}/permissions")
    public KbcResponse listOwnerPermissions(@PathVariable Integer ownerId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("permissions", permmgrSevice.getPermissions(ownerId, null));
        return response;
    }

    @ApiOperation("查询拥有者下的权限列表（不包含关联角色下的权限）")
    @GetMapping(value = "/{ownerId}/permissions", params = "resourceTypeId")
    public KbcResponse listOwnerdDirectPermissions(@PathVariable Integer ownerId,
        @RequestParam Integer resourceTypeId, @RequestParam(required = false) String fuzzyResName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("permissions",
            permmgrSevice.getPermissionsByPermOwnerId(ownerId, resourceTypeId, fuzzyResName));
        return response;
    }
}
