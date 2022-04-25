package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value ="userMapper")
public interface UserMapper {
    @Select("select * from user_info where name = #{name}")
    User_info getUserPswByName(@Param("name") String name);

    @Update("UPDATE user_info SET avatar_url = #{avatarUrl} WHERE name = #{userName}")
    void updateAvatarSql(@Param("avatarUrl") String avatarUrl,@Param("userName") String userName);

    @Update("UPDATE user_info SET token = #{thisToken}, last_login_date = #{nowTime}, last_login_ip = #{thisIp} WHERE name = #{userName}")
    void setTokenAndLogInfo(@Param("thisToken") String thisToken,@Param("nowTime") String nowTime,@Param("thisIp") String thisIp,@Param("userName") String userName);
}