package com.van.serviceImpl;

import com.van.dao.AddrMapper;
import com.van.pojo.Addr;
import com.van.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddrServiceImpl implements AddrService {

    @Autowired
    private AddrMapper addrMapper;


    //修改订单地址
    @Override
    public int updateAddr(String region, String detailed_addressInteger, String consignee, String mobile, Integer address) {
        return addrMapper.updateAddr(region,detailed_addressInteger,consignee,mobile,address);
    }

    @Override
    public int addAddr(Addr addr) {
        return addrMapper.addAddr(addr);
    }


    @Override
    public List<Addr> findAddrById(Integer uid) {
        return addrMapper.findAddrById(uid);
    }
}
