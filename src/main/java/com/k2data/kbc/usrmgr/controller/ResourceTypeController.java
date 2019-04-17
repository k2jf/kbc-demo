package com.k2data.kbc.usrmgr.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.usrmgr.service.ResourceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("资源类型接口")
@RestController
@RequestMapping("/restyps")
public class ResourceTypeController {

    @Autowired
    ResourceTypeService resourceTypeService;

    @ApiOperation("查询资源类型")
    @GetMapping
    public KbcResponse list() {
        KbcResponse response = new KbcResponse();
        response.getBody().put("restyps", resourceTypeService.list());
        return response;
    }
}
