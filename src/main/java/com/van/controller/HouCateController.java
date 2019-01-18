package com.van.controller;

import com.van.pojo.Category;
import com.van.service.CategoryService;
import com.van.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HouCateController {

    @Autowired
    private IndexService is;

    @Autowired
    private  CategoryService cs;



    //跳转到分类页面
    @RequestMapping("/productClass")
    public String productClass(Model mod){
        List<Category> cate= is.findAllCategory();
        mod.addAttribute("cate",cate);
        return "hou/productClass";
    }

    //跳转到添加分类页面
  @RequestMapping("/addProudctClass")
   public String addProductClass(Model mod){
        List<Category> listCate=is.findAllCategory();
        mod.addAttribute("listCate",listCate);
        return "hou/productClass-add";
   }

   //添加商品分类
   @RequestMapping("/updateProduct")
   public  String updateProduct(@RequestParam Integer parentId, String cc_name){
        Category ct=new Category();
          if(!"0".equals(parentId)){
              ct.setCc_parent_id(parentId);
              ct.setCc_name(cc_name);
          }else {
              ct.setCc_name(cc_name);
              ct.setCc_parent_id(parentId);
          }
          int num= cs.addCate(ct);
          if(num>0){
              return "redirect:/productClass";
          }
        return "redirect:/addProudctClass";

   }


   //删除商品二级分类
    @RequestMapping("/delProductClass")
    public String delProductClass(@RequestParam Integer cc_id){
       int num=cs.delCate(cc_id);
           return "redirect:/productClass";

    }


}
