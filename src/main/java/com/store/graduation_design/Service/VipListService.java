package com.store.graduation_design.Service;

import com.store.graduation_design.Pojo.Vip_Info;

import java.util.List;

public interface VipListService {
    List<Vip_Info> getVipListSer(String userName);
    List<Vip_Info> getVipListByPhoneSer(String userName, String vipPhone);
    List<Vip_Info> getVipListByNameSer(String userName, String vipName);
    List<Vip_Info> getVipListByIdSer(String userName, String vipId);
    String putMoneySer(String userName,String putNum,String vipId);
    String deleteVipSer(String userName, String vipId);
}
