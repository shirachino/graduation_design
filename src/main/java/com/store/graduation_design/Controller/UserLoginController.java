package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
    public String getUserPsw(String userName,String userPsw) {
        if (userMapper.getUserPswByName(userName) == null){
            return "590";//登录失败，用户不存在
        }
        User_info thisUser = userMapper.getUserPswByName(userName);
        String thisUserPsw = DigestUtils.md5DigestAsHex(userPsw.getBytes());
        if (thisUser.getPsw().equals(thisUserPsw)){
            return "200";//登录成功
        } else {
            return "591";//登录失败，密码不正确
        }

    }
}