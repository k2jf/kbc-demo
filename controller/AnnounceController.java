package com.k2data.kbc.announce.controller;

import com.k2data.kbc.announce.model.Announce;
import com.k2data.kbc.announce.service.AnnounceService;
import com.k2data.kbc.api.KbcResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("通知管理接口")
@RestController
public class AnnounceController {

    @Autowired
    AnnounceService announceService;

    @ApiOperation("获取通知列表")
    @RequestMapping(value = "announces", method = RequestMethod.GET)
    public KbcResponse list() {
        KbcResponse response = new KbcResponse();
        response.getBody().put("anounces", announceService.list());
        return response;

    }

    @ApiOperation("获取当前应显示的通知列表")
    @RequestMapping(value = "announces/effective", method = RequestMethod.GET)
    public KbcResponse effective() {
        KbcResponse response = new KbcResponse();
        response.getBody().put("anounces", announceService.list());//TODO: 待修改实现
        return response;

    }

    @ApiOperation("创建一个通知")
    @RequestMapping(value = "announces", method = RequestMethod.POST)
    public KbcResponse insert(@RequestBody Announce announce) {
        announceService.insert(announce);
        return KbcResponse.SUCCESS;
    }

}
