package com.van.dao;

import com.van.pojo.Order_Detail;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailMapper {

    //根据订单id查询订单详情
    List<Order_Detail> findOrderDetail(String o_uid);
}
