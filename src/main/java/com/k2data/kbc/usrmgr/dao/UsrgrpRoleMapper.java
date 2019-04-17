package com.k2data.kbc.usrmgr.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsrgrpRoleMapper {

    void insert(Integer roleId, Integer usrgrpId);

    void deleteByUsrgrpId(Integer usrgrpId);

    void deleteByUsrgrpIdAndRoleId(Integer usrgrpId, Integer roleId);

    List<Integer> getUsrgrpIdsByRoleId(Integer roleId);

    List<Integer> getRoleIdsByUsrgrpId(Integer usrgrpId);

    List<Integer> getRoleIdsByUsrgrpIds(List<Integer> usrgrpIds);

}
