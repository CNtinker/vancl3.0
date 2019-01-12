package com.van.pojo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Order {
    private Integer o_uid;      //订单号
    private Integer uid;        //用户id
    private String address;     //地址
    private Float cost;         //总金额
    private Date create_time;   //下单时间
    private Integer o_status;   //订单状态
    private Integer pay_type;   //支付类型
    private Integer pay_state;  //支付状态
    private Date   pay_time;    //支付时间

    private User user;
    private Addr addr;
}