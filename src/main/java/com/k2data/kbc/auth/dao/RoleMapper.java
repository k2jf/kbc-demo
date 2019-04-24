package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.dao.condition.RoleCondition;
import com.k2data.kbc.auth.model.Role;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    void insert(Role role);

    void delete(Integer id);

    List<Role> list(RoleCondition condition);
}
