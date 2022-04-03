package com.store.graduation_design.Controller;

import com.store.graduation_design.Service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegController {

    @Autowired
    private RegService regService;

    @RequestMapping(value = "/userRegister")
    public boolean useInsertFunction(String regName,String regTelNum,String regPsw){
        return regService.insertUser(regName,regTelNum,regPsw);
    }


}
