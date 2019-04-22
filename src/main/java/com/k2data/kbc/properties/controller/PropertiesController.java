package com.k2data.kbc.properties.controller;

import com.k2data.kbc.api.KbcBizException;
import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.properties.model.Properties;
import com.k2data.kbc.properties.service.PropertiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api("key:value管理接口")
@RestController
public class PropertiesController {


    @Autowired
    PropertiesService propertiesService;

    @ApiOperation("list  key:value")
    @GetMapping(value = "propList")
    public KbcResponse list(Properties properties) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", properties.getKey());
        map.put("value", properties.getValue());
        map.put("groupKey", properties.getGroupKey());
        KbcResponse response = new KbcResponse();
        response.getBody().put("audiLogList", propertiesService.list(map));
        return response;
    }

    @ApiOperation("save key:value")
    @PostMapping(value = "saveProps")
    public KbcResponse saveProps(@RequestBody Properties properties) {
        propertiesService.save(properties);
        KbcResponse response = new KbcResponse();
        response.getBody().put("id", properties.getId());
        return response;
    }

    @ApiOperation("delete key:value")
    @DeleteMapping(value = "prop/{id}")
    public KbcResponse delete(@PathVariable int id) throws KbcBizException {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        if (0 != propertiesService.list(map).size()) {
            throw new KbcBizException("Target does not exist");
        }
        propertiesService.deleteByPrimaryKey(id);
        return KbcResponse.SUCCESS;
    }

}
