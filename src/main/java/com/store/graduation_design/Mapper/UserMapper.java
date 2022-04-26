package com.store.graduation_design.Mapper;

import com.store.graduation_design.Pojo.User_info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Component(value ="userMapper")
public interface UserMapper {
    /**
     * @Todo 根据用户名查询用户所有信息
     */
    @Select("select * from user_info where name = #{name}")
    User_info getUserPswByName(@Param("name") String name);

    /**
     * @Todo 更改用户头像路径
     */
    @Update("UPDATE user_info SET avatar_url = #{avatarUrl} WHERE name = #{userName}")
    void updateAvatarSql(@Param("avatarUrl") String avatarUrl,@Param("userName") String userName);

    /**
     * @Todo 用户登录设置Token，登录时间和IP
     */
    @Update("UPDATE user_info SET token = #{thisToken}, last_login_date = #{nowTime}, last_login_ip = #{thisIp} WHERE name = #{userName}")
    void setTokenAndLogInfo(@Param("thisToken") String thisToken,@Param("nowTime") String nowTime,@Param("thisIp") String thisIp,@Param("userName") String userName);

    /**
     * @Todo 更改用户手机号
     */
    @Update("UPDATE user_info SET phonenum = #{phoneNum} WHERE name = #{userName}")
    void modifyPhone(@Param("phoneNum") String phoneNum,@Param("userName") String userName);

    /**
     * @Todo 更改用户密码
     */
    @Update("UPDATE user_info SET psw = #{userPsw} WHERE name = #{userName}")
    void modifyPsw(@Param("userPsw") String userPsw,@Param("userName") String userName);

    /**
     * @Todo 开通VIP
     */
    @Update("UPDATE user_info SET isvip = '1' WHERE name = #{userName}")
    void regVipSql(@Param("userName") String userName);

    /**
     * @Todo 开通VIP
     */
    @Update("DROP TABLE IF EXISTS ${userName}_stock,${userName}_salerecord,${userName}_vip")
    void dropUserTable(@Param("userName") String userName);

    @Update("DELETE FROM user_info WHERE name = #{userName}")
    void deleteUserInfo(@Param("userName") String userName);
}