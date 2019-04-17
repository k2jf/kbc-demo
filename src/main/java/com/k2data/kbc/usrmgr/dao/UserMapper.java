package com.k2data.kbc.usrmgr.dao;

import com.k2data.kbc.usrmgr.dao.condition.UserCondition;
import com.k2data.kbc.usrmgr.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

    void delete(Integer id);

    void update(User user);

    User getById(Integer id);

    List<User> list(UserCondition condition);
}
