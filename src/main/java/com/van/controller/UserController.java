/*
package com.van.controller;

import com.van.pojo.User;
import com.van.service.UserService;

import com.van.util.RandUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("/index")
    public  String index(){
        return "regs";
    }

    */
/*
     * 注册方法
     * **//*

    @RequestMapping("/regs")
    public String reg(@RequestParam User user,String yzm,String repwd) {
        int num;
        String param = RandUtil.getRandomNum();
        if (yzm == param && user.getPwd() == repwd) {
            user.setReg_date(new Date(new java.util.Date().getTime()));
            user.setUser_type(2);
            user.setState(1);
            num = userService.addUser(user);
            return "redirect:/login";
        }
        return "regs";
    }


    @RequestMapping("/login")
    public String login(User user,HttpServletRequest request){
        User us=userService.findLoginPwdUser(user);
        if(us!=null){
            request.getSession().setAttribute("user",us);
            return "redirect:/index2";
        }
       return "login";
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public String ajax(@RequestParam String login_name){
        User user=userService.findLoginUser(login_name);
        if(user==null){
          return "true";
        }
        return "false";
    }


   /* @RequestMapping(value ="/dx",method =RequestMethod.POST)
/*    @RequestMapping(value ="/dx",method =RequestMethod.POST)
    @ResponseBody
    public String dx(@RequestParam String mobile){
        String param=RandUtil.getRandomNum();
        boolean result;
        result=SendSMSValidate.sendSms(mobile,param);
        System.out.println(mobile);
        System.out.println(param);
        if(result){
            return "true";
        }
        return "flase";
    }*/

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@Param("login_name") String login_name){
       User us= userService.findLoginUser(login_name);
       if(us!=null){
           return "true";
       }
       return "false";
    }

}
*/
