package com.k2data.kbc.tag.controller;

import com.k2data.kbc.api.KbcResponse;
import com.k2data.kbc.tag.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("标签管理")
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;


    @ApiOperation("新增标签")
    @PostMapping("")
    public KbcResponse createTag(@RequestParam String tagname) {
        tagService.addTag(tagname);
        return KbcResponse.SUCCESS;
    }

    @ApiOperation("获取单个标签")
    @GetMapping("/{id}")
    public KbcResponse getTag(@PathVariable Integer id) {
        KbcResponse kbcResponse=new KbcResponse();
        kbcResponse.getBody().put("tag",tagService.getTag(id));
        return kbcResponse;
    }

    @ApiOperation("全部标签")
    @GetMapping("")
    public KbcResponse getTagList() {
        KbcResponse kbcResponse=new KbcResponse();
        kbcResponse.getBody().put("tags",tagService.tagList());
        return kbcResponse;
    }

}
