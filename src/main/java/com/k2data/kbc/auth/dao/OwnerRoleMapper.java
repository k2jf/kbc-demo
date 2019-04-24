package com.k2data.kbc.auth.dao;

import com.k2data.kbc.auth.model.OwnerRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerRoleMapper {

    void insert(OwnerRole ownerRole);

    void deleteByOwnerId(Integer ownerId);

    void deleteByOwnerIdAndRoleId(Integer ownerId, Integer roleId);

    void update(OwnerRole ownerRole);

    List<Integer> getOwnerIdsByRoleId(Integer roleId);

    List<Integer> getRoleIdsByOwnerId(Integer ownerId);

    List<OwnerRole> getByOwnerId(Integer ownerId);
}
