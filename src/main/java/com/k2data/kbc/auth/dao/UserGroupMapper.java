package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.dao.condition.UserGroupCondition;
import com.k2data.kbc.auth.model.UserGroup;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserGroupMapper {

    void insert(UserGroup userGroup);

    void delete(Integer id);

    UserGroup getById(Integer id);

    List<UserGroup> list(UserGroupCondition condition);
}
