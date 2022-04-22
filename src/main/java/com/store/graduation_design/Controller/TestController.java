package com.store.graduation_design.Controller;

import com.store.graduation_design.Utils.MyFormatDate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class TestController {
    @RequestMapping(value = "/test")

    public String testReturn(){
        return DigestUtils.md5DigestAsHex("123456".getBytes());
    }
}
