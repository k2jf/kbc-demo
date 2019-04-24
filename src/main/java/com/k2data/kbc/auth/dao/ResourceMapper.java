package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.dao.condition.ResourceCondition;
import com.k2data.kbc.auth.model.Resource;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {

    Resource getById(Integer id);

    List<Resource> list(ResourceCondition condition);
}
