package com.k2data.kbc.usrmgr.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.usrmgr.service.AuthorizeService;
import com.k2data.kbc.usrmgr.service.request.AuthorizeResourceRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("授权接口")
@RestController
@RequestMapping("/authorizes")
public class AuthorizeController {

    @Autowired
    AuthorizeService authorizeResource;

    @ApiOperation("授权资源")
    @PostMapping
    public KbcResponse authorize(@RequestBody AuthorizeResourceRequest authorizeResourceRequest) {
        authorizeResource.authorizeResource(authorizeResourceRequest);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("查询资源授权")
    @GetMapping
    public KbcResponse list(@RequestParam(required = false) Integer typeId,
        @RequestParam(required = false) String fuzzyResourceName,
        @RequestParam(required = false) Integer roleId,
        @RequestParam(required = false) Integer userId) {
        KbcResponse response = new KbcResponse();
        response.getBody()
            .put("authorizes", authorizeResource.list(typeId, fuzzyResourceName, roleId, userId));
        return response;
    }

    @ApiOperation("删除资源授权")
    @DeleteMapping
    public KbcResponse delete(@RequestParam(required = false) Integer resourceId,
        @RequestParam(required = false) Integer roleId,
        @RequestParam(required = false) Integer userId) {
        authorizeResource.delete(resourceId, roleId, userId);
        return KbcResponse.SUCCESS;
    }
}
