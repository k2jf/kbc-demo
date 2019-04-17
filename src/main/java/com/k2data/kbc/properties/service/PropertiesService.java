package com.k2data.kbc.properties.service;

import com.k2data.kbc.properties.model.Properties;
import java.util.List;
import java.util.Map;

public interface PropertiesService {

    List<Properties> list(Map<String, Object> map);

    void save(Properties Properties);

    void deleteByPrimaryKey(Integer id);

}
