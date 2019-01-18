package com.van.service;

import com.van.pojo.Category;
import com.van.pojo.Img;
import com.van.pojo.Product;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2018/12/21.
 */
public interface CategoryService {
    //查询所有的商品
    List<Product> findAllById(Map<String, Object> map);

    //查询所有商品的图片
    List<Img> findImgByPrant_class(String PrantClass);


    //根据分类id查询所属父类id
    int findPid(Integer cc_id);

    //添加分类
    int addCate(Category ctg);

    //根据cc_id删除分类
    int delCate(Integer cc_id);
    /*根据商品名称模糊查询*/
    List<Product> findAllByFuzzyP_name(String search);

}


