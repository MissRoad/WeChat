package com.bing.mapper;

import com.bing.entity.FlBind;

public interface FlBindMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fl_bind
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fl_bind
     *
     * @mbggenerated
     */
    int insert(FlBind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fl_bind
     *
     * @mbggenerated
     */
    int insertSelective(FlBind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fl_bind
     *
     * @mbggenerated
     */
    FlBind selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fl_bind
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FlBind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fl_bind
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FlBind record);

    /**
     * 根据员工id获取员工绑定的openid
     * @param userCode
     * @return
     */
    FlBind selectOpenIdByUid(String userCode);
}