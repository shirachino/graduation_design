package com.store.graduation_design.Controller;

import com.store.graduation_design.Mapper.UserMapper;
import com.store.graduation_design.Pojo.User_info;
import com.store.graduation_design.Pojo.User_upload;
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
    private UserMapper userMapper;

    @RequestMapping("/uploadAvatar")
    public User_upload uploadAvatar(@RequestParam("userName") String userName, @RequestParam("file") MultipartFile file) throws IOException {
        String fileExtension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));//拓展名
        String originalFilename = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."));//文件名
        String nowDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());//日期
        String fileName = userName + "-" + nowDate + fileExtension;
        String path = "E:/WebWorkspace/spring-test/src/main/resources/static/img/userHead/";
        //检查该路径对应的目录是否存在. 如果不存在则创建目录
        User_upload resObj = new User_upload();
        File newFile = new File(path);
        if (!newFile.exists()) {
            if (newFile.mkdirs()){
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
            userMapper.updateAvatarSql("/static/img/userHead/" + fileName,userName);
            return resObj;
        } else {
            resObj.setStatus("691");
            resObj.setUrl("http://localhost:8080/static/img/userHead/" + fileName);
            return resObj;
        }
    }

    @RequestMapping(value = "/getUserInfo")
    public User_info getUserInfo(String userName){
        return userMapper.getUserPswByName(userName);
    }
}
