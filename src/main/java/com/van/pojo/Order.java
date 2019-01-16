package com.van.pojo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
public class Order {
    private String o_uid;      //订单号
    private Integer uid;        //用户id
    private Integer address;     //地址
    private Double cost;         //总金额
    private Date create_time;   //下单时间
    private Integer o_status;   //订单状态
    private Integer pay_type;   //支付类型
    private Integer pay_state;  //支付状态
    private Date   pay_time;    //支付时间

    private User user;
    private Addr addr;

    //一条订单有多条订单详情
    private List<Order_Detail> orderLitems;

}