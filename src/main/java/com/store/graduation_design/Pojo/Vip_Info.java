package com.store.graduation_design.Pojo;

import lombok.Data;

/**
 * 会员信息DTO
 */
@Data
public class Vip_Info {
    private Integer vip_id;
    private String vip_name;
    private String vip_phone;
    private String vip_join_date;
    private String vip_birthday;
    private Double vip_point;
    private Double vip_balance;
    private Integer vip_times;
}
