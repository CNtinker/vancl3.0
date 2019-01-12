package com.van.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.van.pojo.User;
import com.van.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HouUserController {

    @Autowired
    private UserService userService;


    /*
    * 用做跳转后台的首页方法
    * **/
    @RequestMapping("/shou")
    public String shou(){
        return "hou/index";
    }


    /*
    * 后台管理用户页面
    * **/
    @RequestMapping("/users")
    public String findUser(@RequestParam Map<String,Object> map, Model mod){
        String pageIndex=(String)map.get("pageIndex");
        if(pageIndex==null) {
            pageIndex = "1";
        }
        //引用分页插件
        Page<Object> pg=PageHelper.startPage(Integer.parseInt(pageIndex),5);
        //多条件查询所有用户
        List<User> listUser=userService.findAllUsers(map);
        mod.addAttribute("pg",pg);
        mod.addAttribute("listUser",listUser);
        mod.addAttribute("map",map);
        return "hou/user";
    }

    /*
    * 点击用户状态进入到下面这方法进入修改用户状态页面
    * **/
    @RequestMapping("/zt")
   public String zt(@RequestParam Integer uid,Model mod){
        mod.addAttribute("uid",uid);
       return "hou/zt";
   }

   /*
   * 更新用户状态的方法
   * **/
   @RequestMapping("/gx")
   public String gx(@RequestParam Integer state,Integer uid){
      Integer num= userService.updateUserState(state,uid);
      return "redirect:/users";
   }

}
