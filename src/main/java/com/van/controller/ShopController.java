package com.van.controller;

import com.van.pojo.Shopcar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2019/1/11.
 */

@Controller
@RequestMapping(path = "/ShopController")
public class ShopController {


    @RequestMapping("/ShopAddProductNum")
    @ResponseBody
    public void ShopAddProductNum(String pid, HttpSession session){

        //取到session中的购物车集合
        List<Shopcar> listcar=(List<Shopcar>)session.getAttribute("listcar");
        //取出session中的金额总和
        double sumMoney=(double)session.getAttribute("sumMoney");
        //取出session中的商品数量
        int Carnum=(Integer) session.getAttribute("Carnum");
        for (Shopcar sc:listcar) {
            if (sc.equals("pid")){
                sumMoney=sumMoney+sc.getProduct().getP_discount_price();
                sc.setNumber(sc.getNumber()-1);
                if(sc.getNumber()<1){
                    Carnum=Carnum-1;
                    listcar.remove(sc);
                    break;
                }
            }
        }
        session.setAttribute("Carnum",Carnum);
        session.setAttribute("sumMoney",sumMoney);
        //判断购物车里是否存在以及里面是否还有商品
        //没有就删除，有继续存
        if (listcar.isEmpty()||sumMoney==0){
            listcar.clear();
            session.setAttribute("listcar",listcar);
        }else{
            session.setAttribute("listcar",listcar);
        }

    }

}
