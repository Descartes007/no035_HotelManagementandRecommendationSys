package com.example.mapper;

import com.example.entity.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作collect相关数据接口
*/
public interface CollectMapper {

    /**
      * 新增
    */
    int insert(Collect collect);

    @Select("select * from collect where user_id = #{id}")
    List<Collect> selectByUserId(Integer id);

    @Select("select * from collect where user_id = #{userId} and type_id = #{typeId} ")
    Collect selectByUserIdAndType(@Param("userId") Integer userId, @Param("typeId") Integer typeId);

    @Delete("delete from collect where type_id = #{typeId}")
    void deleteByTypeId(Integer typeId);

    List<Collect> selectAll(Collect collect);
}