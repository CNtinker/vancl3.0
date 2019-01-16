package com.van.service;


import com.van.pojo.Addr;

import java.util.List;

public interface AddrService {

    //根据id修改地址
    int updateAddr(String region,String detailed_addressInteger,String consignee,String mobile, Integer address);

    //添加地址
    int addAddr(Addr addr);

    //根据用户id查询地址
    List<Addr> findAddrById(Integer uid);
}
