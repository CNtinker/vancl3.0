package com.van.controller;

import com.van.pojo.Img;
import com.van.pojo.P_pk_children;
import com.van.pojo.Product;
import com.van.service.CategoryService;
import com.van.service.ProductService;
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
    @Autowired
    private CategoryService cs;
    @Autowired
    private ProductService ps;

    /*前往商品详情的方法*/
    @RequestMapping("/gotoProduct")
    public String gotoProduct(@RequestParam Map<String,Object> map, HttpSession session){
        List<Product> pds=(List<Product>) session.getAttribute("pds");
        for(Product p:pds){
            if(p.getP_id()==Integer.parseInt((String)map.get("p_id"))){
                loadPicture(p);
                loadcolorandsize(p);
                session.setAttribute("product",p);

            }
        }
        return "Product";
    }
    /*加载商品详情的图片的方法*/
    private void loadPicture(Product p){
        String PrantClass="product/detail/"+p.getP_img();
        List<Img> imgList=cs.findImgByPrant_class(PrantClass);
        /*插入小图*/
        List<String> imgs=new ArrayList<>();

        for (Img img:imgList) {
            String img_path=img.getImg_path();
            imgs.add("http://pkydxd0tb.bkt.clouddn.com/"+img_path);
        }
        p.setDetailimg(imgs);
    }
    /*加载商品的属性(尺码与颜色)*/
    private void loadcolorandsize(Product p){
        /*用于存储color值集合*/
        List<String> colors=new ArrayList<String>();
        /*查询出对应(商品)的(颜色)对应表(P_pk_children),存入List*/
        Integer biao_id=1;
        List<P_pk_children> colorlist=ps.findP_Pk_childrensByP_id(p.getP_id(),biao_id);
        /*遍历colorlist,通过里面的Value查询对应的颜色值存入颜色list(colors);*/
        for (P_pk_children color:colorlist) {
            colors.add(ps.findColorByColorId(color.getValue()));
        }
        p.setColors(colors);

        /*如法炮制查询出尺码*/

 /*用于存储color值集合*/
        List<String> sizes=new ArrayList<String>();
        /*查询出对应(商品)的(颜色)对应表(P_pk_children),存入List*/
        biao_id=2;
        List<P_pk_children> sizelist=ps.findP_Pk_childrensByP_id(p.getP_id(),biao_id);
        /*遍历colorlist,通过里面的Value查询对应的颜色值存入颜色list(colors);*/
        for (P_pk_children size:sizelist) {
            sizes.add(ps.findSizeByColorId(size.getValue()));
        }
        p.setSizes(sizes);


    }

}
