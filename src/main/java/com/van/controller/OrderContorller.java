package com.van.controller;

import com.van.pojo.Addr;
import com.van.pojo.Order;
import com.van.pojo.User;

import com.van.service.AddrService;
import com.van.service.OrderService;
import com.van.service.OrderDetailService;
import com.van.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dzw on 2019/1/17.
 */
@Controller
public class OrderContorller {
    @Autowired
    AddrService addrService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    /*前往个人订单页面*/
    @RequestMapping("/goToOrderIndex")
    public String goToOrderIndex(){
        return "userinfo";
    }
    /*公共部分前往修改个人资料*/
    @RequestMapping("/goToMyData")
    public String goToMyData(){
        return "OrderIndex_pub/UserInFo_pub.html";
    }
    /*修改个人资料的方法*/
    @RequestMapping("/updataMyData")
    public String updataMyData(String TrueName,String sex,String bYear,String bMonth,String bDay,String Email,String Province,String City,String Area,String Address,String Mobile,String Phone,HttpSession session){
        User user= (User)session.getAttribute("user");
        System.out.println(sex);
        if(TrueName!="") {
            user.setReal_name(TrueName);
        }
        if(Integer.parseInt(sex)!=0) {

            user.setSex(Integer.parseInt(sex));

        }
        if(bYear!=""&&bMonth!=""&&bDay!="") {

            if (Integer.parseInt(bMonth)<10){
                bMonth="0"+bMonth;
            }
            if (Integer.parseInt(bDay)<10){
                bDay="0"+bDay;
            }

            String datastring=bYear+"-"+bMonth+"-"+bDay;
            /*Timestamp.valueOf()*/
            System.out.println(datastring);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try{
                user.setDate_birth(sdf.parse(datastring));
            } catch (ParseException pe){
                System.out.println("添加日期失败");
            }

        }
        if(Email!="") {

            user.setEmail(Email);
        }
        if(Province!=""&&City!=""&&Area!=""&&Address!="") {
            /*地址=省市区*/
            String region=Province+City+Area;
            String detailed_address=Address;
            List<Addr> addrs=addrService.findAddrById(user.getUid());
            //之前有地址
            if(addrs.size()>=0) {
                //循环取出默认地址的id
                for (Addr addr : addrs) {
                    if (addr.getIsDefault() == 1) {
                        addrService.updateAddr(region,detailed_address,addr.getConsignee(),addr.getMobile(),addr.getAddress());
                    }
                }
            }else{
                /*第一次加入地址*/
                Addr addr=new Addr();
                addr.setUid(user.getUid());
                addr.setRegion(region);
                addr.setDetailed_address(detailed_address);
                if (user.getReal_name()!=null){
                    addr.setConsignee(user.getReal_name());
                }else{
                    addr.setConsignee(user.getLogin_name());
                }
                    addrService.addAddr(addr);
            }
            user.setReal_name(TrueName);
        }
        if(Mobile!="") {
            user.setTell_phone(Mobile);
        }
        if(Phone!="") {
            user.setLand_phone(Phone);
        }
        
        userService.updateUserById(user);
        return "redirect:/goToMyData";
    }
    /*公共部分前往修改订单*/
     @RequestMapping("/toOrder")
     public String toOrder(HttpSession session){
         User user=(User) session.getAttribute("user");
         /*1.通过U_id查出用户旗下的所有订单*/
         orderService.findOrderById(user.getUid());
         /*2.通过订单表的o_uid查询到对应的订单详情*/
          orderDetailService.findOrderDetail();
        /* 3.通过订单详情表的p_id查询出对应的商品*/

        /*4~5通过订单详情表的size_id与color_id查询对应表的名称*/

        /*6通过订单表的地址id查询出对应的地址名称（详细名称）*/


        return "order";
     }


}
