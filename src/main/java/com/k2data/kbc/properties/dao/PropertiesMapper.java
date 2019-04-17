package com.k2data.kbc.properties.dao;

import com.k2data.kbc.properties.model.Properties;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PropertiesMapper {

    void insert(Properties object);

    List<Properties> list(Map<String, Object> map);

    void deleteByPrimaryKey(Integer id);
}