package com.van.controller;

import com.van.pojo.Img;
import com.van.pojo.Product;
import com.van.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2018/12/27.
 */
@Controller
public class ProductController {


    /*前往商品详情的方法*/
    @RequestMapping("/gotoProduct")
    public String gotoProduct(@RequestParam Map<String,Object> map, HttpSession session){
        List<Product> pds=(List<Product>) session.getAttribute("pds");
        for(Product p:pds){
            if(p.getP_id()==Integer.parseInt((String)map.get("p_id"))){
                /*loadPicture(p);*/
                session.setAttribute("product",p);

            }
        }
        return "Product";
    }



}
