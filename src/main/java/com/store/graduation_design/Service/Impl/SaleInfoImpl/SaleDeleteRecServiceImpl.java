package com.store.graduation_design.Service.Impl.SaleInfoImpl;

import com.store.graduation_design.Mapper.SalesListMapper;
import com.store.graduation_design.Service.SaleInfoService.SaleDeleteRecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleDeleteRecServiceImpl implements SaleDeleteRecService {
    @Autowired
    private SalesListMapper salesListMapper;

    @Override
    public String deleteSaleRecord(String userName,String salesId)throws RuntimeException{
        try {
            salesListMapper.deleteSalesRecord(userName,salesId);
            return "Delete sale record success. Status: 200";
        } catch ( RuntimeException e ){
            return "Delete sale record error. Status: 500";
        }
    }
}
