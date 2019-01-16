package com.van.serviceImpl;

import com.van.dao.OrderMapper;
import com.van.pojo.Order;
import com.van.pojo.Order_Detail;
import com.van.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int updateOrder(Integer uid) {
        return orderMapper.updateOrder(uid);
    }

    @Override
    public int delOrder(Integer o_uid) {
        return orderMapper.delOrder(o_uid);
    }

    //查询所有的订单
    @Override
    public List<Order> findAllOrder(Map<String, Object> map) {
        return orderMapper.findAllOrder(map);
    }

    @Override
    public int updateOrderState(Integer o_status,Integer o_uid) {
        return orderMapper.updateOrderState(o_status,o_uid);
    }

    @Override
    public boolean creatOrder(Order order) {
        int num1=0;
        int num2=0;
        for (Order_Detail od:order.getOrderLitems()){
            num1=11*orderMapper.creatOrderDetail(od);
        }
        if (num1!=0){
            num2=orderMapper.creatOrder(order);
        }

        return num2>0?true:false;
    }
}
