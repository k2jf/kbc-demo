package com.k2data.kbc.tag.service.impl;

import com.k2data.kbc.tag.dao.TagMapper;
import com.k2data.kbc.tag.model.Tag;
import com.k2data.kbc.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> tagList() {
        return tagMapper.list(new Tag());
    }

    @Override
    public Tag getTag(Integer id) {
        return tagMapper.selectById(id);
    }

    @Override
    public int addTag(String tagName) {
        Tag oldTag=tagMapper.selectByName(tagName);
        if(oldTag == null){
            Tag tag=new Tag();
            tag.setTagName(tagName);
            tag.setNum(1);
            return tagMapper.insert(tag);
        }else {
            oldTag.setNum(oldTag.getNum()+1);
            return tagMapper.update(oldTag);
        }

    }
}
