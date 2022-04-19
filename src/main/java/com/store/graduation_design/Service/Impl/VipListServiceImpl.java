package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.VipListMapper;
import com.store.graduation_design.Pojo.Vip_Info;
import com.store.graduation_design.Service.VipListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VipListServiceImpl implements VipListService {
    @Autowired
    private VipListMapper vipListMapper;

    @Override
    public List<Vip_Info> getVipListSer(String userName) {
        return vipListMapper.getVipListSql(userName);
    }

    @Override
    public List<Vip_Info> getVipListByPhoneSer(String userName, String vipPhone) {
        if(vipPhone.length() == 13){
            List<Vip_Info> oneVip =  new LinkedList<>();
            oneVip.add(vipListMapper.getVipByPhoneSql(userName, vipPhone));
            return oneVip;
        } else {
            return vipListMapper.getVipListByPhoneSql(userName,vipPhone);
        }
    }

    @Override
    public List<Vip_Info> getVipListByNameSer(String userName, String vipName) {
        return vipListMapper.getVipListByNameSql(userName,vipName);
    }

    @Override
    public List<Vip_Info> getVipListByIdSer(String userName, String vipId) {
        return vipListMapper.getVipListByIdSql(userName,vipId);
    }

    /**
     * @Todo VIP充值
     */
    @Override
    public String putMoneySer(String userName, String putNum, String vipId) throws RuntimeException{
        try {
            vipListMapper.putMoneySql(userName,putNum,vipId);
            return "200";
        } catch (RuntimeException e){
            return "500";
        }
    }

    /**
     * @Todo 注销VIP
     */
    @Override
    public String deleteVipSer(String userName, String vipId) throws RuntimeException{
        try {
            vipListMapper.deleteVipSql(userName,vipId);
            return "200";
        } catch (RuntimeException e){
            return "500";
        }
    }
}
