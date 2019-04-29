package com.k2data.kbc.announce.dao;

import com.k2data.kbc.announce.model.Announce;
import com.k2data.kbc.announce.model.AnnounceExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface AnnounceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int countByExample(AnnounceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int deleteByExample(AnnounceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int insert(Announce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int insertSelective(Announce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    List<Announce> selectByExample(AnnounceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    Announce selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") Announce record, @Param("example") AnnounceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int updateByExample(@Param("record") Announce record, @Param("example") AnnounceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int updateByPrimaryKeySelective(Announce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_announce
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    int updateByPrimaryKey(Announce record);
}