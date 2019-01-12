package com.van.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.van.pojo.Order;
import com.van.service.OrderService;
import com.van.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HouOrderController {

    @Autowired
    private OrderService orderService;

     //查询所有的订单
    @RequestMapping("/order")
    public String Order(@RequestParam Map<String,Object> map,Model mod){
        String pageIndex=(String)map.get("pageIndex");
        if(pageIndex==null){
            pageIndex= "1";
        }
        //引用分页插件
        Page<Object> pg=PageHelper.startPage(Integer.parseInt(pageIndex),5);
        //调用查询所有的订单方法
        List<Order> orders=orderService.findAllOrder(map);
        mod.addAttribute("orders",orders);
        mod.addAttribute("pg",pg);
        mod.addAttribute("map",map);
        return "hou/order";
    }
    //跳转到下单状态页面去
    @RequestMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam Integer o_uid,Model mod){
        mod.addAttribute("o_uid",o_uid);
        return "hou/orderStatus";
    }

    //下单状态修改
    @RequestMapping("/updateStatus")
    public String updateStatus(@RequestParam Integer o_uid,Integer o_status){
      int num=orderService.updateOrderState(o_status,o_uid);
      return "redirect:/order";
    }

    @RequestMapping("/updateOrder")
    public String updateOrder(){

        return "hou/order-modify";
    }


}
