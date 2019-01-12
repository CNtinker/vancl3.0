package com.van.controller;

import com.van.pojo.Img;
import com.van.pojo.P_pk_children;
import com.van.pojo.Product;
import com.van.pojo.Shopcar;
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

    @RequestMapping("/addToShoppingCar")
    public String addToShoppingCar(@RequestParam String color,String size,String Pronum,HttpSession session){
        System.out.println("color:"+color+"size:"+size+"num:"+Pronum);
        //从session中提取出商品（product）
        Product product=(Product)session.getAttribute("product");
        //从session中提取出购物车集合
        Object obj=session.getAttribute("listcar");
        //设置总金额(默认为0)
        double sumMoney=0;
        //创建购物车数量
        int Carnum = 0;
        //创建shopcar对象
        Shopcar sc=new Shopcar();
        //将商品以及属性加入shopcar中
        sc.setProduct(product);
        sc.setColor(color);
        sc.setSize(size);
        sc.setNumber(Integer.parseInt(Pronum));
        //判断是否为第一次进入
        List<Shopcar> listcar=null;
        //购物车为空，第一次进入
        if(obj==null){
            listcar=new ArrayList<Shopcar>();
            //将shopcar加入购物车集合
            listcar.add(sc);
            //小计=总金额(因为第一次进)
            double xiaoji=sc.getXiaoji();
            sumMoney=xiaoji;
            /*购物车数量等于listcar长度*/
            Carnum=listcar.size();
            session.setAttribute("listcar",listcar);
            session.setAttribute("Carnum",Carnum);
            session.setAttribute("sumMoney",sumMoney);
        }else{
            //从session中取出listcar
            listcar=(List<Shopcar>)obj;
            //1.先取出原先存进去的数据
            Carnum=(Integer) session.getAttribute("Carnum");
            sumMoney=(double)session.getAttribute("sumMoney");
            //2.准备一个标记，用于判断是否已有商品
            boolean ishas=true;
            //循环遍历购物车与传进来的商品相匹配
            for (Shopcar shopcar:listcar) {
                //遍历购物车，查看是否有相同的商品
                if (shopcar.getProduct().getP_id().equals(product.getP_id())
                        &&shopcar.getColor().equals(sc.getColor())
                        &&shopcar.getSize().equals(sc.getSize())){
                    //1.数量增加（原先+新增的）
                    shopcar.setNumber(shopcar.getNumber()+sc.getNumber());
                    //总金额=原先的+新增的小计
                    sumMoney=sumMoney+sc.getXiaoji();
                    session.setAttribute("sumMoney",sumMoney);
                    ishas=false;
                    break;
                }
            }
            /*没有相同的商品*/
            if(ishas){
                Carnum=Carnum+1;
                sumMoney=sumMoney+sc.getXiaoji();
                listcar.add(sc);
                session.setAttribute("listcar",listcar);
                session.setAttribute("Carnum",Carnum);
                session.setAttribute("sumMoney",sumMoney);
            }


        }
        return "/shopping";
    }

}
