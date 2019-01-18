package com.van.controller;

import com.van.pojo.Addr;
import com.van.pojo.User;

import com.van.service.AddrService;
import com.van.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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

    UserService userService;

    @RequestMapping("/goToOrderIndex")
    public String goToOrderIndex(){
        return "userinfo";
    }

    @RequestMapping("/goToMyData")
    public String goToMyData(){
        return "OrderIndex_pub/UserInFo_pub.html";
    }

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
}
