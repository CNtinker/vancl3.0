package com.van.service;

import com.van.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderService {

    //查询所有的订单
    List<Order> findAllOrder(Map<String, Object> map);

    //根据订单id修改下单状态
    int updateOrderState(@Param("o_status") Integer o_status, @Param("o_uid") Integer o_uid);

    //根据用户id修改订单信息
    int updateOrder(Integer uid);
}
