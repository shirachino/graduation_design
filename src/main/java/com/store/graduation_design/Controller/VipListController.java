package com.store.graduation_design.Controller;

import com.store.graduation_design.Pojo.Vip_Info;
import com.store.graduation_design.Service.VipListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VipListController {
    @Autowired
    private VipListService vipListService;

    @RequestMapping(value = "/getVipList")
    public List<Vip_Info> getVipList(String userName){
        return vipListService.getVipListSer(userName);
    }

    @RequestMapping(value = "/searchVipByPhone")
    public List<Vip_Info> getVipListByPhone(String userName,String vipInfo){
        return vipListService.getVipListByPhoneSer(userName,vipInfo);
    }

    @RequestMapping(value = "/searchVipByName")
    public List<Vip_Info> getVipListByName(String userName,String vipInfo){
        return vipListService.getVipListByNameSer(userName,vipInfo);
    }

    @RequestMapping(value = "/searchVipById")
    public List<Vip_Info> getVipListById(String userName,String vipInfo){
        return vipListService.getVipListByIdSer(userName,vipInfo);
    }

    /**
     * @Todo VIP充值
     */
    @RequestMapping(value = "/putMoneyById")
    public String putMoney(String userName, String putNum, String vipId){
        return vipListService.putMoneySer(userName,putNum,vipId);
    }

    /**
     * @Todo 注销VIP
     */
    @RequestMapping(value = "/deleteVipById")
    public String deleteVip(String userName,String vipId){
        return vipListService.deleteVipSer(userName,vipId);
    }
}
