package com.van.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.van.pojo.*;
import com.van.service.AddrService;
import com.van.service.OrderService;
import com.van.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    AddrService addrService;

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

    @RequestMapping("/ShopDelProductNum")
    @ResponseBody
    public String ShopDelProductNum(String pid,String color,String size, HttpSession session) {
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
                sumMoney=sumMoney-sc.getXiaoji();
                Carnum=Carnum-1;
                listcar.remove(sc);
                break;

            }
        }
        json.add(String.valueOf(Carnum));
        json.add(String.valueOf(sumMoney));
        session.setAttribute("Carnum",Carnum);
        session.setAttribute("sumMoney",sumMoney);
        if (listcar.isEmpty()||sumMoney==0){
            listcar.clear();
            session.setAttribute("listcar",listcar);
            return  JSONArray.toJSONString(json);
        }else{
            session.setAttribute("listcar",listcar);
            return  JSONArray.toJSONString(json);
        }
    }

    @RequestMapping("/CheckoutProduct")
    public String CheckoutProduct(HttpSession session){
        /*判断用户是否登录*/
        logstatus(session);

        if((Boolean) session.getAttribute("logstatus")){

            return "redirect:/sheng";
        }else{
            /*跳转到登录页面*/
            return "redirect:/login";
        }

    }


    @RequestMapping("/ShopjiesuanProduct")
    public String ShopjiesuanProduct(HttpSession session, Model model){
        String jieguo=jiesuan(session);
        Double sumMoney=(Double) session.getAttribute("sumMoney");
        session.setAttribute("sumMoney",0);
        System.out.println(jieguo);
        model.addAttribute("dingdanhao",jieguo);
        model.addAttribute("sumMoney",sumMoney);
        return  "success";
    }

//判断用户是否登录的小方法
    private void logstatus(HttpSession session)
    {
        User user=(User) session.getAttribute("user");
        boolean logstatus=false;
        if(user!=null){
            logstatus=true;
        }
        //登录状态logstatus
        session.setAttribute("logstatus", logstatus);
    }

    //结算方法
    private String jiesuan(HttpSession session) {
        //启动本方法要用的业务逻辑层方法

        System.out.println("订单生成...");
        //从session中取出购物车
        List<Shopcar> sc=(List<Shopcar>)session.getAttribute("listcar");
        //取出session中的金额总和
        double sumMoney=(double)session.getAttribute("sumMoney");
        //从session中取出user
        User user=(User)session.getAttribute("user");
        //1.创建一个订单对象
        Order o=new Order();
        //创建一个随机数作为订单表的id
        int num=(int)Math.floor(Math.random()*1000);
        String salNo="SL"+System.currentTimeMillis()+num;
        o.setO_uid(salNo);


        /*user.setUid(1);*/
        o.setUid(user.getUid());


        /*根据用户地址或传进来的地址
        去查地址表id*/
       List<Addr> addrs= addrService.findAddrById(o.getUid());
        for (Addr addr:addrs) {
            if(addr.getIsDefault()==1){
                o.setAddress(addr.getAddress());
            }
        }

        //设置下单时间

        //设置总金额
        o.setCost(sumMoney);
        //设置订单状态(假)
        o.setO_status(1);
        //设置支付类型(假)
        o.setPay_type(1);
        //设置支付状态
        o.setPay_state(1);
        //创建一个装商品详情表的List集合
        List<Order_Detail> list=new ArrayList<Order_Detail>();
        //循环从购物车里取出具体的订单，并向订单详情对象中传值
        for(Shopcar shop:sc){
            //创建订单详情对象
            Order_Detail od=new Order_Detail();
            od.setO_uid(o.getO_uid());
            od.setP_id(shop.getProduct().getP_id());
            od.setOd_number(shop.getNumber());
            /*!!根据商品sizeid与colorid查询对应的表id*/
            od.setSize_id(productService.findSizeIdBySize(shop.getSize()));
            od.setColor_id(productService.findColorIdByColor(shop.getColor()));
            od.setOd_money(shop.getXiaoji());
            list.add(od);
        }

        //将订单详情加入集合设置中去
        o.setOrderLitems(list);
        //调用业务逻辑层方法
        boolean falg=orderService.creatOrder(o);
        //是否结算成功
        if(falg){
            //结算成功，清空购物车
            session.setAttribute("listcar", null);
            /*summoney暂时不删需要显示
            session.setAttribute("sumMoney", 0);*/

            session.setAttribute("Carnum", 0);
            //修改库存数据
            for (Order_Detail eod : list) {
            //调用商品的业务逻辑层的方法
            //參数1：被修改的商品对象，  参数2：修改的库存数量
            productService.updateStockByP_id(eod.getP_id(),productService.findProductById(eod.getP_id()).getP_stock()-eod.getOd_number());

            }
            //成功转发到主页。。(临时)
            return salNo;
        }else{
            //失败转发到shop
            return "结算失败";
        }
    }

    }
