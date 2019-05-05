package com.k2data.kbc.tag.dao;

import com.k2data.kbc.tag.model.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    int insert(Tag tag);

    Tag selectById(Integer id);

    Tag selectByName(String tagName);

    List<Tag> list(Tag tag);

    int update(Tag tag);
}
