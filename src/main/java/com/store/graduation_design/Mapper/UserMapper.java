package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component(value ="userMapper")
public interface UserMapper {
    @Select("select * from user_info where name = #{name}")
    User_info getUserPswByName(@Param("name") String name);
}