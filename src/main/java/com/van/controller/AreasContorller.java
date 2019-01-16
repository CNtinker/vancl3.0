package com.van.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.van.pojo.Areas;
import com.van.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AreasContorller {
    @Autowired
    private AreasService areasService;

   @RequestMapping("/sheng")
    public String sheng(Model mod){
       List<Areas> are=areasService.findAllProvence();
       mod.addAttribute("as",are);
        return "hou/address";
    }

    @RequestMapping("/shi")
    @ResponseBody
    public String shi(@RequestParam Integer ar_parent_id,Model mod){
        String json=JSONArray.toJSONString(areasService.findAllCityByPid(ar_parent_id));
            return json;
   }

    @RequestMapping("/qu")
    @ResponseBody
    public String qu(@RequestParam Integer cr_parent_id, Model mod){
        String jsons=JSONArray.toJSONString(areasService.findAllQuByarid(cr_parent_id));
        return jsons;
    }
}
