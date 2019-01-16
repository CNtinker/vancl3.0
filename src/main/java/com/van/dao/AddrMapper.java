package com.van.dao;

import com.van.pojo.Addr;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AddrMapper {

    //根据id修改地址
    int updateAddr(@RequestParam String region, String detailed_addressInteger, String consignee, String mobile, Integer address);

    //添加地址
    int addAddr(Addr addr);

    //根据用户id查询地址
    List<Addr> findAddrById(Integer uid);
}
