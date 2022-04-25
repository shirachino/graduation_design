package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.RegMapper;
import com.store.graduation_design.Service.RegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegserviceImpl implements RegService {

    @Autowired
    private RegMapper regMapper;

    @Override
    public boolean insertUser(String regName, String regTelNum, String regPsw) {
            //判断用户名,手机是否被注册过，没有则插入，有则返回false
            if (regMapper.isDoubleName(regName) == null && regMapper.isDoublePhone(regTelNum) == null){
                //根据用户名创建用户仓库表
                //regMapper.dropExistStockTable(regName);
                regMapper.createUserStock(regName);
                //创建用户销售表
                //regMapper.dropExistSaleTable(regName);
                regMapper.createUserSaleTable(regName);
                //创建用户销Vip表
                regMapper.createUserVipTable(regName);
                //将用户插入用户表中
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String regTime = df.format(new Date());
                regMapper.insertRegUser(regName,regTelNum,regPsw,regTime);
                return true;
            } else {
                return false;
            }
    }
}
