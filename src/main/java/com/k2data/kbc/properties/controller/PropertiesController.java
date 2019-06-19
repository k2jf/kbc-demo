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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("key:value管理接口")
@RestController
public class PropertiesController {


    @Autowired
    PropertiesService propertiesService;

    @ApiOperation("list  key:value")
    @GetMapping(value = "propList")
    public KbcResponse list() {
        Map<String, Object> map = new HashMap<>();
        KbcResponse response = new KbcResponse();
        response.getBody().put("properties", propertiesService.list(map));
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

}
