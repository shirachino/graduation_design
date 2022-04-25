package com.store.graduation_design.Controller;


import com.store.graduation_design.Service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController

public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    /**
     * @Todo 用户登录接口
     * @param userName 用户名
     * @return 用户信息 判断密码
     */
    @RequestMapping(value = "/userLogin")
    public String getUserPsw(String userName,String userPsw) throws Exception {
        return userLoginService.userLoginSer(userName,userPsw);
    }
}