package com.k2data.kbc.auth.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.auth.service.ResmgrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("资源接口")
@RestController
@RequestMapping("/resmgr")
public class ResmgrController {

    @Autowired
    ResmgrService resmgrService;

    @ApiOperation("查询资源类型")
    @GetMapping("/types")
    public KbcResponse list() {
        KbcResponse response = new KbcResponse();
        response.getBody().put("restyps", resmgrService.types());
        return response;
    }

    @ApiOperation("查询资源")
    @GetMapping("/resources")
    public KbcResponse list(@RequestParam(required = false) Integer typeId,
        @RequestParam(required = false) String fuzzyName) {
        KbcResponse response = new KbcResponse();
        response.getBody().put("resources", resmgrService.list(typeId, fuzzyName));
        return response;
    }
}
