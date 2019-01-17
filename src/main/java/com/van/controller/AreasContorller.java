package com.van.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.van.pojo.Addr;
import com.van.pojo.Areas;
import com.van.pojo.User;
import com.van.service.AddrService;
import com.van.service.AreasService;
import org.apache.ibatis.annotations.Flush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AreasContorller {
    @Autowired
    private AreasService areasService;

    @Autowired
    private AddrService addrService;

    //省
   @RequestMapping("/sheng")
    public String sheng(Model mod, HttpSession session){
      User user=(User) session.getAttribute("user");
       //查询地址
      List<Addr> addr=addrService.findAddrById(user.getUid());
       //查询省的方法
       List<Areas> are=areasService.findAllProvence();
       mod.addAttribute("add",addr);
       mod.addAttribute("as",are);
        return "checkout";
    }
    //市
    @RequestMapping("/shi")
    @ResponseBody
    public String shi(@RequestParam Integer ar_parent_id,Model mod){
        String json=JSONArray.toJSONString(areasService.findAllCityByPid(ar_parent_id));
            return json;
   }
     //区
    @RequestMapping("/qu")
    @ResponseBody
    public String qu(@RequestParam Integer cr_parent_id, Model mod){
        String jsons=JSONArray.toJSONString(areasService.findAllQuByarid(cr_parent_id));
        return jsons;
    }

    //添加地址
    @RequestMapping("/addAddr")
    public String addAddr(@RequestParam String consignee, String dq,
                 String detailed_address,String mobile,Model mod,HttpSession session){

        User user=(User)session.getAttribute("user");
        Addr addr=new Addr();
        addr.setUid(user.getUid());
        addr.setConsignee(consignee);
        addr.setRegion(dq);
        addr.setDetailed_address(detailed_address);
        addr.setMobile(mobile);
        int num=addrService.addAddr(addr);
        return "redirect:/tian";
    }

    @RequestMapping("tian")
    public String tian(){

       return "redirect:/sheng";
    }

}
