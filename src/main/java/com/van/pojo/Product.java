package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by dzw on 2018/12/21.
 */
@Setter
@Getter
public class Product {
    private Integer p_id;
    private String p_name;
    private String p_img;
    private String p_description;
    private Integer p_price;
    private Integer p_discount_price;
    private Integer sales_volume;
    private Integer p_stock;
    private Integer categoryLevel1;
    private Integer categoryLevel2;
    private Integer p_new;
    private Date Release;
    private String fileName;
    private Integer isDelete;
    /*用于记录文件图片数量*/
    private Integer fenleinum;
    private Integer jieshaonum;
}
