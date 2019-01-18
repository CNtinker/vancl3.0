package com.van.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.van.pojo.Category;
import com.van.pojo.Img;
import com.van.pojo.Product;
import com.van.service.CategoryService;
import com.van.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2018/12/20.
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService is;
    @Autowired
    private CategoryService cs;

    @RequestMapping("/index2")
    public String index2(HttpSession session){
        if(session.getAttribute("list")==null) {
            /*加载分页的方法*/
            List<Category> list = is.findAllCategory();
            session.setAttribute("list", list);
            /*加载广告轮播的方法*/
            List<String> gglblist=logingglblist();
            session.setAttribute("gglblist",gglblist);
        }
        return "index";
    }
    /*用于加载广告的方法*/
    private List<String> logingglblist(){
        /*新建一个String类型的List用于接收网址*/
        List<String> gglblist=new ArrayList<String>();
        /*网址所属的类*/
        String PrantClass="index_pub/img/gglb/";
        /**
        *1.根据PrantClass查询图片对象，
        *2.并循环遍历查询出图片路径与千牛云网址结合，
        *3.最后加入gglblist，并返回。
        **/
        List<Img> imgList=is.findImgByPrantClass(PrantClass);
        for (Img img:imgList) {
            String img_path=img.getImg_path();
            gglblist.add("http://pkr49wiq4.bkt.clouddn.com/"+img_path);
        }
        return gglblist;
    }

    @RequestMapping("/HeaderSearch")
    public String HeaderSearch(@RequestParam Map<String,Object> map, String search,Model mod){
        if(search!=null||search!="") {
            /*取到页码(pageIndex),并判断*/
            String pageIndex=(String)map.get("pageIndex");
            if(pageIndex==null) {
                pageIndex = "1";
            }
            /*根据查询条件排序*/
            Page<Object> pg= PageHelper.startPage(Integer.parseInt(pageIndex),5/*,"p_id desc"*/);

            /*模糊查询*/
            List<Product> pds=cs.findAllByFuzzyP_name(search);
            for (Product pd:pds) {
                loadPicture(pd);
            }

            mod.addAttribute("pg",pg);
            mod.addAttribute("pds",pds);
            return "search";
        }else{
            return "redirect:/index2";
        }

    }
    private void loadPicture(Product pd){
        /*网址所属的类*/
        String PrantClass="product/"+pd.getP_img();
        List<Img> imgList=cs.findImgByPrant_class(PrantClass);
        for (Img img:imgList) {
            String img_path=img.getImg_path();
            pd.setPicturesmain("http://pkr49wiq4.bkt.clouddn.com/"+img_path);
        }
    }
}
