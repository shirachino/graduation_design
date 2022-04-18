package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController

public class UserLoginController {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Todo 用户登录接口
     * @param userName 用户名
     * @return 用户信息 判断密码
     */
    @RequestMapping(value = "/userLogin")
    public User_info getUserPsw(String userName) {
        return userMapper.getUserPswByName(userName);
    }
}