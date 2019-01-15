package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dzw on 2019/1/15.
 */
@Getter
@Setter
public class Order_Detail {
    private Integer od_id;
    private String o_uid;
    private Integer p_id;
    private Integer od_number;
    private Integer size_id;
    private Integer color_id;
    private Double od_money;
}
