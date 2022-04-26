package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.RegMapper;
import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import com.store.graduation_design.Pojo.User_upload;
import com.store.graduation_design.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegMapper regMapper;

    @Override
    public User_upload uploadAvatarSer(String userName, MultipartFile file) throws IOException {
        String fileExtension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));//拓展名
        String originalFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));//文件名
        String nowDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());//日期
        String fileName = userName + "-" + nowDate + fileExtension;
        String path = "E:/WebWorkspace/spring-test/src/main/resources/static/img/userHead/";
        //检查该路径对应的目录是否存在. 如果不存在则创建目录
        User_upload resObj = new User_upload();
        File newFile = new File(path);
        if (!newFile.exists()) {
            if (newFile.mkdirs()) {
                resObj.setStatus("200"); //新建成功
            } else {
                resObj.setStatus("690");
            }
        }
        String filePath = path + fileName;
        //System.out.println("filePath: " + filePath);

        //保存文件
        File dest = new File(filePath);
        if (!(dest.exists())) {
            file.transferTo(dest);
            resObj.setStatus("201"); //保存成功
            resObj.setUrl("http://localhost:8080/static/img/userHead/" + fileName);
            userMapper.updateAvatarSql("/static/img/userHead/" + fileName, userName);
            return resObj;
        } else {
            resObj.setStatus("691");
            resObj.setUrl("http://localhost:8080/static/img/userHead/" + fileName);
            return resObj;
        }
    }

    @Override
    public User_info getUserInfoSer(String userName) throws RuntimeException {
        try {
            return userMapper.getUserPswByName(userName);
        } catch (RuntimeException e) {
            return new User_info();
        }
    }

    @Override
    public String modifyUserInfoSer(String userName, String phoneNum) throws RuntimeException {
        try {
            if (regMapper.isDoublePhone(phoneNum) == null){
                userMapper.modifyPhone(phoneNum, userName);
                return "200";
            } else {
                return "590";//有人使用
            }
        } catch (RuntimeException e) {
            return "500";
        }
    }

    @Override
    public String modifyUserPswSer(String userName,String oldPsw,String newPsw) throws RuntimeException {
        try {
            String oldMD5Psw = DigestUtils.md5DigestAsHex(oldPsw.getBytes());
            if (oldMD5Psw.equals(userMapper.getUserPswByName(userName).getPsw())){
                String newMD5Psw = DigestUtils.md5DigestAsHex(newPsw.getBytes());
                userMapper.modifyPsw(newMD5Psw, userName);
                return "200";
            } else {
                return "590";
            }
        } catch (RuntimeException e) {
            return "500";
        }
    }

    @Override
    public String regVipSer(String userName) throws RuntimeException {
        try{
            userMapper.regVipSql(userName);
            return "200";
        } catch (RuntimeException e){
            return "500";
        }
    }

    @Override
    public String deleteAccountSer(String userName) throws RuntimeException {
        try{
            userMapper.dropUserTable(userName);
            userMapper.deleteUserInfo(userName);
            return "200";
        } catch (RuntimeException e){
            return "500";
        }
    }
}
