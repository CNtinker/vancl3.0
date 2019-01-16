package com.van.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.van.pojo.Category;

import com.van.pojo.Product;
import com.van.service.CategoryService;
import com.van.service.IndexService;
import com.van.service.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class HouProductContorller {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService cs;

    @Autowired
    private IndexService is;

    /*
     * 后台管理显示所有商品页面的代码
     * **/
    @RequestMapping("/product")
    public String product(@RequestParam Map<String, Object> map, Model mod) {
        String pageIndex = (String) map.get("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }
        //引用分页插件
        Page<Object> pg = PageHelper.startPage(Integer.parseInt(pageIndex), 5);
        //查询所有商品
        List<Product> pds = cs.findAllById(map);
        mod.addAttribute("pds", pds);
        mod.addAttribute("pg", pg);
        mod.addAttribute("map", map);
        return "hou/product";
    }

    //作为跳转到添加商品的一个页面
    @RequestMapping("/addProduct")
    public String productAdd(Model mod, HttpSession session) {
        List<Category> list = is.findAllCategory();
        session.setAttribute("list", list);
        return "hou/product-add";
    }

    /*//添加商品的方法
    @RequestMapping("addProduct")
    public String addProduct(MultipartFile files,Product pd) throws IOException {
        System.out.println("标题："+pd.getP_name());
        System.out.println("分类："+pd.getCategoryLevel2());
        System.out.println("价格："+pd.getP_price());
        System.out.println("优惠："+pd.getP_discount_price());
        System.out.println("库存："+pd.getP_stock());
        System.out.println("描述："+pd.getP_description());
        byte[] imgBytes = files.getBytes();
        //获取上传到七牛云上的图片路径
        String imgUrl = Qiniu.upLoadImage(imgBytes);
        //根据分类id查询父类id
        int pid=cs.findPid(pd.getCategoryLevel2());
        pd.setCategoryLevel1(pid);
        pd.setP_img(imgUrl);
        pd.setP_new(1);
        pd.setIsDelete(1);
        pd.setRelease(new Date(new java.util.Date().getTime()));
        //调用添加商品方法
        int num=productService.addProduct(pd);
        if(num>0){
            return "/product";
        }
        return "/productAdd";
    }*/



   //商品修改页面
   @RequestMapping("/modifProduct")
   public String modifyProduct(@RequestParam Integer p_id,Model mod){
       List<Category> list=is.findAllCategory();
       Product pdts= productService.findProductById(p_id);
       mod.addAttribute("pdts",pdts);
       mod.addAttribute("category",list);
       return "hou/product-modify";
   }



}
