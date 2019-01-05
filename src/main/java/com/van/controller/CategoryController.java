package com.van.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.van.pojo.Category;
import com.van.pojo.Product;
import com.van.service.CategoryService;
import com.van.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2018/12/21.
 */
@SessionAttributes(value = "pds")
@Controller
public class CategoryController {
    @Autowired
    private CategoryService cs;
    @Autowired
    private IndexService is;

    @RequestMapping("/fenlei")
    public String fenlei(@RequestParam Map<String,Object> map,Model mod,HttpSession session){
        /*没有分类获取分类*/
        if(session.getAttribute("list")==null) {
            List<Category> list = is.findAllCategory();
            session.setAttribute("list", list);
        }
        /*取到页码(pageIndex),并判断*/
        String pageIndex=(String)map.get("pageIndex");
        if(pageIndex==null) {
            pageIndex = "1";
        }
        String categoryLevel1=(String)map.get("categoryLevel1");
        String categoryLevel2=(String)map.get("categoryLevel2");
        /*如果categoryLevel1为空（点击categoryLevel2）,向categoryLevel1里存入所对应的categoryLevel1*/
        if(categoryLevel1==null){
            List<Category> list2=(List<Category>)session.getAttribute("list");
            for(Category l2:list2){
                for(Category l3:l2.getChile()){
                    if(l3.getCc_id()==Integer.parseInt(categoryLevel2)){
                        mod.addAttribute("categoryLevel1",l3.getCc_parent_id());
                        mod.addAttribute("categoryLevel2",categoryLevel2);
                    }
                }
            }
        }


        /*根据查询条件排序*/
        Page<Object> pg=PageHelper.startPage(Integer.parseInt(pageIndex),5/*,"p_id desc"*/);

        List<Product> pds= cs.findAllById(map);
        mod.addAttribute("pg",pg);
        mod.addAttribute("pds",pds);
        if(categoryLevel1!=null){
            return "fenlei2";
        }
        return "search";

    }
}
