
package com.van.controller;

import com.van.pojo.User;
import com.van.service.UserService;

import com.van.util.RondomNumUtil;
import com.van.util.SendSMSValidate;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public  String index(){
        return "regs";
    }


    //短信验证
    @ResponseBody
    @RequestMapping("/getCode")
    public String getCode(@RequestParam String phone,HttpSession session){
        System.out.println("进入短信登陆验证。。。。。"+phone);
        String param = RondomNumUtil.createRandomVcode();
        session.setAttribute("param",param);
        Boolean flag = SendSMSValidate.sendSms(phone,param);
        if(flag){
            return "true";
        }
        return "false";
    }


    /*
     * 注册方法
     * **/

  @RequestMapping("/regs")
    public String reg(@RequestParam String yzm, User user,HttpSession session) {
        String param=(String) session.getAttribute("param");
        if ((yzm).equals(param)) {
            user.setUser_type(2);
            user.setState(1);
           int num = userService.addUser(user);
            return "redirect:/login";
        }
        return "redirect:/index";
    }
    @RequestMapping("/login")
    public  String login(){
        return "login";
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestParam String loginName,String pwd, HttpServletRequest request){
         User user=userService.findLoginPwdUser(loginName,pwd);
         if(user!=null){
             request.getSession().setAttribute("user",user);
             if(user.getUser_type()==1){
                 return "redirect:/shou";
             }else {
                 return "redirect:/index2";
             }
         }else {
             return "redirect:/login";
         }

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

