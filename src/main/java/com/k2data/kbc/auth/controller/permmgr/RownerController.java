package com.k2data.kbc.auth.controller.permmgr;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.service.PermmgrSevice;
import com.k2data.kbc.auth.service.request.ConfigOwnerRolesRequest;
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

@Api("权限管理接口-角色用户关联")
@RestController
@RequestMapping("/permmgr/rowners")
public class RownerController {
    @Autowired
    PermmgrSevice permmgrSevice;

    @ApiOperation("删除拥有者关联的角色")
    @DeleteMapping("/{roleOwnerId}/roles")
    public KbcResponse deleteOwnerRoles(@PathVariable Integer roleOwnerId,
        @RequestParam String roleIds) {
        permmgrSevice.deleteOwnerRoles(roleOwnerId, roleIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("更新拥有者的角色")
    @PutMapping(value = "/{roleOwnerId}/roles", params = "roleIds")
    public KbcResponse modifyOwnerRoles(@PathVariable Integer roleOwnerId,
        @RequestParam String roleIds) {
        permmgrSevice.modifyOwnerRoles(roleOwnerId, roleIds);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("配置拥有者的角色")
    @PutMapping(value = "/{roleOwnerId}/roles", params = "action=config")
    public KbcResponse configOwnerRoles(@PathVariable Integer roleOwnerId,
        @RequestParam String roleIds,
        @RequestBody ConfigOwnerRolesRequest configOwnerRolesRequest) {
        permmgrSevice.configOwnerRoles(roleOwnerId, roleIds, configOwnerRolesRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询拥有者下的角色列表")
    @GetMapping("/{rownerId}/roles")
    public KbcResponse listOwnerRoles(@RequestParam(required = false) String fuzzyName,
        @PathVariable Integer rownerId) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("ownerRoles", permmgrSevice.listOwnerRoles(fuzzyName, rownerId));
        return response;
    }
}
