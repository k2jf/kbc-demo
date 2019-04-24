package com.k2data.kbc.auth.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserUsrgrpMapper {

    void insert(Integer userId, Integer usrgrpId);

    void deleteByUserId(Integer userId);

    void deleteByUserIdAndUsrgrpId(Integer userId, Integer usrgrpId);

    List<Integer> getUsrgrpIdsByUserId(Integer userId);

    List<Integer> getUserIdsByUsrgrpId(Integer usrgrpId);
}
