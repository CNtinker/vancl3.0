package com.van.dao;


import com.van.pojo.Order;
import com.van.pojo.Order_Detail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {

    //查询所有的订单
    List<Order> findAllOrder(Map<String, Object> map);

   //根据订单id修改订单状态
   int updateOrderState(@Param("o_status") Integer o_status, @Param("o_uid") Integer o_uid);

   //根据用户id修改订单信息
    int updateOrder(Integer uid);

    /*根据订单详情实体类创建订单详情*/
    int creatOrderDetail(Order_Detail order_detail);

    //根据订单实体类创建订单
    int creatOrder(Order order);

    //根据id删除订单
    int delOrder(Integer o_uid);
}
