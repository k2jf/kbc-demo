package com.k2data.kbc.announce.service;

import com.k2data.kbc.announce.dao.AnnounceMapper;
import com.k2data.kbc.announce.model.Announce;
import com.k2data.kbc.announce.model.AnnounceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnounceService {

    @Autowired
    protected AnnounceMapper mapper;

    public void insert(Announce model) {
        mapper.insert(model);
    }

    public void deleteByPrimaryKey(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void updateByPrimaryKey(Announce model) {
        mapper.updateByPrimaryKey(model);
    }

    public Announce selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Announce> list() {
        return mapper.selectByExample(new AnnounceExample());
    }

}
