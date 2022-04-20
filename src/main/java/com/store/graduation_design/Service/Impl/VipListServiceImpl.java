package com.store.graduation_design.Service.Impl;

import com.store.graduation_design.Mapper.VipListMapper;
import com.store.graduation_design.Pojo.Status_return;
import com.store.graduation_design.Pojo.Vip_Info;
import com.store.graduation_design.Service.VipListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static com.store.graduation_design.Utils.MyFormatDate.yyyyMMddNowDay;
import static com.store.graduation_design.Utils.MyFormatDate.yyyyNowYear;

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
        if (vipPhone.length() == 13) {
            List<Vip_Info> oneVip = new LinkedList<>();
            oneVip.add(vipListMapper.getVipByPhoneSql(userName, vipPhone));
            return oneVip;
        } else {
            return vipListMapper.getVipListByPhoneSql(userName, vipPhone);
        }
    }

    @Override
    public List<Vip_Info> getVipListByNameSer(String userName, String vipName) {
        return vipListMapper.getVipListByNameSql(userName, vipName);
    }

    @Override
    public List<Vip_Info> getVipListByIdSer(String userName, String vipId) {
        return vipListMapper.getVipListByIdSql(userName, vipId);
    }

    /**
     * @Todo VIP充值
     */
    @Override
    public String putMoneySer(String userName, String putNum, String vipId) throws RuntimeException {
        try {
            vipListMapper.putMoneySql(userName, putNum, vipId);
            return "200";
        } catch (RuntimeException e) {
            return "500";
        }
    }

    /**
     * @Todo 注销VIP
     */
    @Override
    public String deleteVipSer(String userName, String vipId) throws RuntimeException {
        try {
            vipListMapper.deleteVipSql(userName, vipId);
            return "200";
        } catch (RuntimeException e) {
            return "500";
        }
    }

    @Override
    public Status_return vipRegisterSer(String userName, String vipName, String vipPhone, String vipBirthday, Double vipBalance) throws RuntimeException {
        Status_return resObj = new Status_return();
        try {
            Integer nowYear = Integer.parseInt(yyyyNowYear()) * 100000;
            Integer thisVipId = nowYear + vipListMapper.vipNoSql(userName, nowYear) + 1;
            resObj.setData(thisVipId.toString());

            String vipJoinDate = yyyyMMddNowDay(0);

            vipListMapper.vipRegisterSql(userName, thisVipId, vipName, vipPhone, vipJoinDate, vipBirthday, vipBalance);

            resObj.setStatus("200");

            return resObj;
        } catch (RuntimeException e) {
            resObj.setData("服务器异常，注册VIP失败:\n" + e.getMessage());
            resObj.setStatus("500");
            return resObj;
        }
    }

    @Override
    public Boolean isPhoneUsedSer(String userName, String vipPhone) {
        return vipListMapper.getVipByPhoneSql(userName, vipPhone) != null;
    }
}
