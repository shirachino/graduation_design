package com.store.graduation_design.Controller;

import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    @RequestMapping(value = "/test")
    public String testReturn(){
        return DigestUtils.md5DigestAsHex("123456".getBytes());
    }
}
