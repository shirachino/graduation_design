package com.store.graduation_design.Pojo;

import lombok.Data;

@Data
public class User_info {
    private Integer id;
    private String name;
    private String phonenum;
    private String psw;
    private String isvip;
    private String regtime;
    private String token;
    private String avatar_url;
}
