package com.van.serviceImpl;

import com.van.dao.OrderDetailMapper;
import com.van.pojo.Order_Detail;
import com.van.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public List<Order_Detail> findOrderDetail(String o_uid) {
        return orderDetailMapper.findOrderDetail(o_uid);
    }
}
