package com.k2data.kbc.usrmgr.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.usrmgr.service.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("资源接口")
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @ApiOperation("查询资源")
    @GetMapping
    public KbcResponse list(@RequestParam(required = false) Integer typeId,
        @RequestParam(required = false) String fuzzyName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("resources", resourceService.list(typeId, fuzzyName));
        return response;
    }
}
