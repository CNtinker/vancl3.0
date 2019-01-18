package com.van.service;

import com.van.pojo.Order_Detail;

import java.util.List;

public interface OrderDetailService {
    //根据订单id查询订单详情
    List<Order_Detail> findOrderDetail(String o_uid);

}
