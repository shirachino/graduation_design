package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserLoginController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/userLogin")
    public User_info getUserPsw(String userName) {
        return userMapper.getUserPswByName(userName);
    }
}
