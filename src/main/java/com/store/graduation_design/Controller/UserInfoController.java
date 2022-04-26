package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import com.store.graduation_design.Pojo.User_upload;
import com.store.graduation_design.Service.UserInfoService;
import com.store.graduation_design.Utils.MyFormatDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/uploadAvatar")
    public User_upload uploadAvatar(@RequestParam("userName") String userName, @RequestParam("file") MultipartFile file) throws IOException {
        return userInfoService.uploadAvatarSer(userName,file);
    }

    @RequestMapping(value = "/getUserInfo")
    public User_info getUserInfo(String userName){
        return userInfoService.getUserInfoSer(userName);
    }

    @RequestMapping(value = "/modifyUserInfo")
    public String modifyUserInfo(String userName,String phoneNum){
        return userInfoService.modifyUserInfoSer(userName,phoneNum);
    }

    @RequestMapping(value = "/modifyUserPsw")
    public String modifyUserPsw(String userName,String oldPsw,String newPsw){
        return userInfoService.modifyUserPswSer(userName,oldPsw,newPsw);
    }

    @RequestMapping(value = "/becomeVip")
    public String becomeVip(String userName){
        return userInfoService.regVipSer(userName);
    }

    @RequestMapping(value = "/deleteAccount")
    public String deleteAccount(String userName){
        return userInfoService.deleteAccountSer(userName);
    }
}
