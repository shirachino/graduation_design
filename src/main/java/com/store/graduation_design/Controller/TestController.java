package com.store.graduation_design.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    @RequestMapping(value = "/test")
    public String testReturn(){
        return "success";
    }
}
