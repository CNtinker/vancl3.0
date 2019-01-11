package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by dzw on 2019/1/9.
 */

public class Shopcar {
    //产品对象
    private Product product;
    //对象颜色
    private String Color;
    //对象尺寸
    private String Size;
    //产品数量
    private int number=1;
    //产品小计
    private double xiaoji=0;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getXiaoji() {
        return xiaoji=getNumber()*getProduct().getP_discount_price();
    }

    public void setXiaoji(double xiaoji) {
        this.xiaoji = xiaoji;
    }
}
