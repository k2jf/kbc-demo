package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.model.ResourceType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourceTypeMapper {

    List<ResourceType> list();
}
