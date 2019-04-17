package com.k2data.kbc.usrmgr.dao;

import com.k2data.kbc.usrmgr.model.ResourceType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceTypeMapper {

    List<ResourceType> list();
}
