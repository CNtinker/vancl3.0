package com.van.service;


import com.van.pojo.Addr;

public interface AddrService {

    //根据id修改地址
    int updateAddr(String region,String detailed_addressInteger,String consignee,String mobile, Integer address);
}
