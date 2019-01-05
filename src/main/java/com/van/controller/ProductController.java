package com.van.controller;

import com.van.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
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
                loadPicture(p);
                session.setAttribute("product",p);
            }
        }
        return "Product";
    }

    /*加载详情页面的图片附属方法*/
    private void loadPicture(Product p){
        int L1=p.getCategoryLevel1();
        int L2=p.getCategoryLevel2();
        String p_img=p.getP_img();
        /*设置默认分类图片文件数量*/
        int fenleinum=0;

        int jieshaonum=0;

        File file= new File("F:\\workspace_test\\vangit2\\src\\main\\resources\\static\\ProductList\\"+L1+"\\"+L2+"\\"+p_img+"\\fenlei");
        fenleinum=file.listFiles().length;
        p.setFenleinum(fenleinum);
        File file2= new File("F:\\workspace_test\\vangit2\\src\\main\\resources\\static\\ProductList\\"+L1+"\\"+L2+"\\"+p_img+"\\jieshao");
        jieshaonum=file2.listFiles().length;
        p.setJieshaonum(jieshaonum);
    }

}
