package com.van.controller;

import com.van.pojo.User;
import com.van.service.UserService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping("/index")
    public  String index(){
        return "order";
    }

    @RequestMapping("/regs")
    public String reg(User user){
        if(userService.addUser(user)>0){
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
