package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Addr {
    private  Integer address;
    private  Integer uid;
    private  String region;
    private  String detailed_address;
    private  String consignee;
    private  String mobile;
    private  Integer isDefault;
    private  String remark;
}
