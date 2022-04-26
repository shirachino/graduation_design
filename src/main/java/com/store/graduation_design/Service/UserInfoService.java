package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.User_info;
import com.store.graduation_design.Pojo.User_upload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserInfoService {
    User_upload uploadAvatarSer(String userName, MultipartFile file) throws IOException;
    User_info getUserInfoSer(String userName);
    String modifyUserInfoSer(String userName,String phoneNum);
    String modifyUserPswSer(String userName,String oldPsw,String newPsw);
    String regVipSer(String userName);
    String deleteAccountSer(String userName);
}
