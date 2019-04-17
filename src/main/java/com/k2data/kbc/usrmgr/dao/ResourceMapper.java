package com.k2data.kbc.usrmgr.dao;

import com.k2data.kbc.usrmgr.dao.condition.ResourceCondition;
import com.k2data.kbc.usrmgr.model.Resource;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceMapper {

    List<Resource> getByTypeId(Integer typeId);

    List<Resource> list(ResourceCondition condition);
}
