package com.store.graduation_design.Controller;

import com.store.graduation_design.Service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegController {

    @Autowired
    private RegService regService;

    /**
     * @Todo 注册接口
     * @param regName 用户名
     * @param regTelNum 手机号
     * @param regPsw 密码
     * @return 布尔值
     */
    @RequestMapping(value = "/userRegister")
    public boolean useInsertFunction(String regName,String regTelNum,String regPsw){
        String RegMD5Psw = DigestUtils.md5DigestAsHex(regPsw.getBytes());//加密密码
        return regService.insertUser(regName,regTelNum,RegMD5Psw);
    }


}
