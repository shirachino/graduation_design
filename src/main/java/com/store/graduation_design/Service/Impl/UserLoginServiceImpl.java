package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import com.store.graduation_design.Service.UserLoginService;
import com.store.graduation_design.Utils.IPAddressUtils;
import com.store.graduation_design.Utils.MyFormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String userLoginSer(String userName, String userPsw) throws Exception {
        try {
            if (userMapper.getUserPswByName(userName) == null) {
                return "590";//登录失败，用户不存在
            }
            User_info thisUser = userMapper.getUserPswByName(userName);
            String thisUserPsw = DigestUtils.md5DigestAsHex(userPsw.getBytes());

            if (thisUser.getPsw().equals(thisUserPsw)) {
                //根据用户名+IP地址生产Token
                String TimeMills = String.valueOf(System.currentTimeMillis());
                String thisIp = IPAddressUtils.getLocalIP();
                String nameAndIP = userName + thisIp;
                String thisToken = DigestUtils.md5DigestAsHex(nameAndIP.getBytes());
                userMapper.setTokenAndLogInfo(thisToken,TimeMills,thisIp,userName);
                return "200";//登录成功
            } else {
                return "591";//登录失败，密码不正确
            }
        } catch (Exception e) {
            return "500";
        }
    }
}
