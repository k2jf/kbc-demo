package com.k2data.kbc.tag.service;

import com.k2data.kbc.tag.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> tagList();

    Tag getTag(Integer id);

    int addTag(String tagName);
}
