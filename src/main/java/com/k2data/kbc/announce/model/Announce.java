package com.k2data.kbc.announce.model;

import java.util.Date;

public class Announce {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.id
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.content
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.remark
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.start_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.end_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.creator
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kbc_announce.create_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.id
     *
     * @return the value of kbc_announce.id
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.id
     *
     * @param id the value for kbc_announce.id
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.content
     *
     * @return the value of kbc_announce.content
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.content
     *
     * @param content the value for kbc_announce.content
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.remark
     *
     * @return the value of kbc_announce.remark
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.remark
     *
     * @param remark the value for kbc_announce.remark
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.start_time
     *
     * @return the value of kbc_announce.start_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.start_time
     *
     * @param startTime the value for kbc_announce.start_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.end_time
     *
     * @return the value of kbc_announce.end_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.end_time
     *
     * @param endTime the value for kbc_announce.end_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.creator
     *
     * @return the value of kbc_announce.creator
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.creator
     *
     * @param creator the value for kbc_announce.creator
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kbc_announce.create_time
     *
     * @return the value of kbc_announce.create_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kbc_announce.create_time
     *
     * @param createTime the value for kbc_announce.create_time
     *
     * @mbggenerated Thu Mar 07 18:56:30 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}