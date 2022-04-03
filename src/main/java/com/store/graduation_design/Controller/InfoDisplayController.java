package com.store.graduation_design.Controller;

import com.store.graduation_design.Service.InfoDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoDisplayController {

    @Autowired
    private InfoDisplayService infoDisplayService;

    @RequestMapping(value = "/infoDisplay")
    public String getInfo(String userName) throws RuntimeException {
        try {
            //今日顾客数、今日销售额
            return infoDisplayService.getDisplayInfo(userName);
        } catch (RuntimeException e){
            return "Request Time-out HTTP 408";
        }

    }
}
