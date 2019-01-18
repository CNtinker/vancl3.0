package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dzw on 2019/1/15.
 */
@Getter
@Setter
public class Order_Detail {
    private Integer od_id;   //订单详情id
    private String o_uid;    //订单id
    private Integer p_id;    //商品id
    private Integer od_number;  //商品数量
    private Integer size_id;
    private Integer color_id;
    private Double od_money;

    private Product pd;
}
