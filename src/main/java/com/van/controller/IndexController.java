package com.van.controller;

import com.van.pojo.Category;
import com.van.pojo.Img;
import com.van.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dzw on 2018/12/20.
 */
@Controller
public class IndexController {
    @Autowired
    private IndexService is;


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

}
