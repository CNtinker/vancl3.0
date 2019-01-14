package com.van.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.van.pojo.Shopcar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2019/1/11.
 */

@Controller
@RequestMapping(path = "/ShopController")
public class ShopController {


    @RequestMapping("/ShopReduceProductNum")
    @ResponseBody
    public String ShopReduceProductNum(String pid,String color,String size, HttpSession session){
        System.out.println(pid);
        System.out.println(color);
        System.out.println(size);
        List<String> json=new ArrayList<String>();
        //取到session中的购物车集合
        List<Shopcar> listcar=(List<Shopcar>)session.getAttribute("listcar");
        //取出session中的金额总和
        double sumMoney=(double)session.getAttribute("sumMoney");
        //取出session中的商品数量
        int Carnum=(Integer) session.getAttribute("Carnum");
        for (Shopcar sc:listcar) {
            if (String.valueOf(sc.getProduct().getP_id()).equals(pid)
                    &&sc.getColor().equals(color)
                    &&sc.getSize().equals(size)
                    ){
                sumMoney=sumMoney-sc.getProduct().getP_discount_price();
                sc.setNumber(sc.getNumber()-1);
                if(sc.getNumber()<1){
                    Carnum=Carnum-1;
                    listcar.remove(sc);
                    break;
                }
                json.add(String.valueOf(sc.getNumber()));
                json.add(String.valueOf(sc.getXiaoji()));
            }
        }
        json.add(String.valueOf(Carnum));
        json.add(String.valueOf(sumMoney));
        session.setAttribute("Carnum",Carnum);
        session.setAttribute("sumMoney",sumMoney);
        //判断购物车里是否存在以及里面是否还有商品
        //没有就删除，有继续存
        if (listcar.isEmpty()||sumMoney==0){
            listcar.clear();
            session.setAttribute("listcar",listcar);
            return  JSONArray.toJSONString(json);
        }else{
            session.setAttribute("listcar",listcar);
            return  JSONArray.toJSONString(json);
        }

    }

    @RequestMapping("/ShopAddProductNum")
    @ResponseBody
    public String ShopAddProductNum(String pid,String color,String size, HttpSession session){
        System.out.println(pid);
        System.out.println(color);
        System.out.println(size);

        List<String> json=new ArrayList<String>();
        //取到session中的购物车集合
        List<Shopcar> listcar=(List<Shopcar>)session.getAttribute("listcar");
        //取出session中的金额总和
        double sumMoney=(double)session.getAttribute("sumMoney");
        for (Shopcar sc:listcar) {
            if (String.valueOf(sc.getProduct().getP_id()).equals(pid)
                    &&sc.getColor().equals(color)
                    &&sc.getSize().equals(size)
                ){
                sumMoney=sumMoney+sc.getProduct().getP_discount_price();
                sc.setNumber(sc.getNumber()+1);
                if(sc.getNumber()>sc.getProduct().getP_stock()){
                    break;
                }
                json.add(String.valueOf(sc.getNumber()));
                json.add(String.valueOf(sc.getXiaoji()));
            }
        }
        json.add(String.valueOf(sumMoney));
        session.setAttribute("sumMoney",sumMoney);
        session.setAttribute("listcar",listcar);
        return  JSONArray.toJSONString(json);
    }
}
