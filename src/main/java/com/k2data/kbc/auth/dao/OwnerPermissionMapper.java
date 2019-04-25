package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.dao.condition.OwnerPermissionCondition;
import com.k2data.kbc.auth.model.OwnerPermission;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerPermissionMapper {

    void insert(OwnerPermission ownerPermission);

    void deleteByOwnerId(Integer ownerId);

    void update(OwnerPermission ownerPermission);

    void delete(Integer ownerId, Integer resourceId);

    List<OwnerPermission> list(OwnerPermissionCondition condition);

    List<OwnerPermission> getByOwnerId(Integer ownerId);
}
