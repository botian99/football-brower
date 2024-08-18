package com.oddfar.campus.business.mapper;

import com.oddfar.campus.business.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select({
            "<script>",
            "SELECT user_id, user_name, nick_name, avatar",
            "FROM sys_user",
            "WHERE user_id IN",
            "<foreach collection='userIds' item='userId' open='(' close=')' separator=','>",
            "#{userId}",
            "</foreach>",
            "</script>"
    })
    List<UserEntity> selectUsersByIds(@Param("userIds") List<Long> userIds);
}
