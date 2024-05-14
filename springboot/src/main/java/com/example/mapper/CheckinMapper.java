package com.example.mapper;

import com.example.entity.Checkin;

import java.util.List;

/**
 * 操作入住相关数据接口
*/
public interface CheckinMapper {

    /**
      * 新增
    */
    int insert(Checkin checkin);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Checkin checkin);

    /**
      * 根据ID查询
    */
    Checkin selectById(Integer id);

    /**
      * 查询所有
    */
    List<Checkin> selectAll(Checkin checkin);

}