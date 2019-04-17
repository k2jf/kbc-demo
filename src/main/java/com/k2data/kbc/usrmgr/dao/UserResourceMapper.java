package com.k2data.kbc.usrmgr.dao;

import com.k2data.kbc.usrmgr.dao.condition.UserResourceCondition;
import com.k2data.kbc.usrmgr.model.UserResource;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserResourceMapper {

    void insert(UserResource userResource);

    void deleteByUserId(Integer userId);

    void delete(Integer userId, Integer resourceId);

    List<UserResource> getByUserId(Integer userId);

    List<UserResource> list(UserResourceCondition condition);
}
