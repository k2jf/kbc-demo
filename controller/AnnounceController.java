package com.k2data.kbc.announce.controller;

import com.k2data.kbc.announce.model.Announce;
import com.k2data.kbc.announce.service.AnnounceService;
import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcException;
import com.k2data.kbc.api.KbcResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("通知管理接口")
@RestController
public class AnnounceController {

    @Autowired
    AnnounceService announceService;

    @ApiOperation("获取通知列表")
    @GetMapping(value = "announces")
    public KbcResponse list() {
        KbcResponse response = new KbcResponse();
        response.getBody().put("announces", announceService.list());
        return response;

    }

    @ApiOperation("获取通知详情")
    @GetMapping(value = "announces/{id}")
    public KbcResponse get(@PathVariable int id) throws KbcBizException {
        KbcResponse response = new KbcResponse();
        Announce announce = announceService.selectByPrimaryKey(id);
        if (announce == null) {
            throw new KbcBizException("Target does not exist");
        }
        response.getBody().put("anounce", announce);
        return response;

    }

    @ApiOperation("获取当前应显示的通知列表")
    @GetMapping(value = "announces/effective")
    public KbcResponse effective() {
        KbcResponse response = new KbcResponse();
        response.getBody().put("announces", announceService.list());//TODO: 待修改实现
        return response;

    }

    @ApiOperation("创建一个通知")
    @PostMapping(value = "announces")
    public KbcResponse insert(@RequestBody Announce announce) {
        announceService.insert(announce);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("修改一个通知")
    @PutMapping(value = "announces/{id}")
    public KbcResponse edit(@PathVariable int id, @RequestBody Announce announce) throws KbcBizException {
        if (id != announce.getId()) {
            throw new KbcBizException("ID doesn't not match");
        }
        announceService.updateByPrimaryKey(announce);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("删除一个通知")
    @DeleteMapping(value = "announces/{id}")
    public KbcResponse delete(@PathVariable int id) throws KbcBizException {
        if (announceService.selectByPrimaryKey(id) == null) {
            throw new KbcBizException("Target does not exist");
        }
        announceService.deleteByPrimaryKey(id);
        return KbcResponse.SUCCESS;
    }

}
