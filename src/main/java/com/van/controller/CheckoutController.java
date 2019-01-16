package com.van.controller;

import com.van.pojo.Addr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dzw on 2019/1/16.
 */
@Controller
public class CheckoutController {

    @RequestMapping(value = "/addAddr")
    public void addAddr(@RequestParam String FullName){
        System.out.println(FullName);
    }


}
