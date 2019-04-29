package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.dao.condition.UserCondition;
import com.k2data.kbc.auth.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

    void delete(Integer id);

    void update(User user);

    User getById(Integer id);

    User getByName(String name);

    List<User> list(UserCondition condition);
}
