package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
    private double p_price;
    private double p_discount_price;
    private Integer sales_volume;
    private Integer p_stock;
    private Integer categoryLevel1;
    private Integer categoryLevel2;
    private Integer p_new;
    private Date Release;
    private String fileName;
    private Integer isDelete;

    /*记录图片路径*/
    private String Picturesmain;
    private List<String> detailimg;

    /*商品有的颜色表*/
    private List<String> colors;
    private List<String> sizes;

}
