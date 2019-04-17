package com.k2data.kbc.usrmgr.dao;

import com.k2data.kbc.usrmgr.dao.condition.RoleResourceCondition;
import com.k2data.kbc.usrmgr.model.RoleResource;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleResourceMapper {

    void insert(RoleResource roleResource);

    void delete(Integer roleId, Integer resourceId);

    List<RoleResource> getByRoleIds(List<Integer> roleIds);

    List<RoleResource> list(RoleResourceCondition condition);
}
