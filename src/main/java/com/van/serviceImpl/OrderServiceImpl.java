package com.van.serviceImpl;

import com.van.dao.OrderMapper;
import com.van.pojo.Order;
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

    //查询所有的订单
    @Override
    public List<Order> findAllOrder(Map<String, Object> map) {
        return orderMapper.findAllOrder(map);
    }

    @Override
    public int updateOrderState(Integer o_status,Integer o_uid) {
        return orderMapper.updateOrderState(o_status,o_uid);
    }
}
